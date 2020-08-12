package Concurrency;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;
import java.util.stream.*;

import static java.lang.Thread.*;

//https://winterbe.com/posts/2015/04/30/java8-concurrency-tutorial-synchronized-locks-examples/

public class LocksExample
{
	static int count = 0;

	public static void main(String[] args)
	{
		//		exampleReentrantLock();
		//		exampleReadWriteLock();
		//		exampleStampedLock();
		//		exampleStampedLockOptimistic();
		//		exampleStampedReadToWriteLock();
		exampleSemaphores();
	}

	//The class ReentrantLock is a mutual exclusion lock with the same basic behavior
	// as the implicit monitors accessed via the synchronized keyword but with extended capabilities.
	// As the name suggests this lock implements reentrant characteristics just as implicit monitors.

	// If another thread has already acquired the lock subsequent calls to lock()
	// pause the current thread until the lock has been unlocked.
	// Only one thread can hold the lock at any given time.
	static void exampleReentrantLock()
	{
		ReentrantLock lock = new ReentrantLock();
		ExecutorService executor = Executors.newFixedThreadPool(2);

		executor.submit(() -> {
			lock.lock();
			try
			{
				sleep(1000);
			}
			catch(InterruptedException e)
			{
				e.printStackTrace();
			}
			finally
			{
				lock.unlock();
			}
		});

		executor.submit(() -> {
			System.out.println("Locked: "
				+lock.isLocked());
			System.out.println("Held by me: "
				+lock.isHeldByCurrentThread());
			boolean locked = lock.tryLock();
			System.out.println("Lock acquired: "
				+locked);
		});

		stop(executor);
	}

	// The interface ReadWriteLock specifies another type of
	// lock maintaining a pair of locks for read and write access.
	// The idea behind read-write locks is that it's usually safe to read mutable variables
	// concurrently as long as nobody is writing to this variable.
	// So the read-lock can be held simultaneously by multiple threads
	// as long as no threads hold the write-lock.
	// This can improve performance and throughput in case that reads are more frequent than writes.

	static void exampleReadWriteLock()
	{
		ExecutorService executor = Executors.newFixedThreadPool(2);
		Map<String, String> map = new HashMap<>();
		ReadWriteLock lock = new ReentrantReadWriteLock();

		executor.submit(() -> {
			lock.writeLock().lock();
			try
			{
				sleep(1000);
				map.put("foo", "bar");
				System.out.println("Readwrite lock Writing");
			}
			catch(InterruptedException e)
			{
				e.printStackTrace();
			}
			finally
			{
				lock.writeLock().unlock();
			}
		});

		// After the write lock has been released both read tasks
		// are executed in parallel and
		// print the result simultaneously to the console

		Runnable readTask = () -> {
			lock.readLock().lock();
			try
			{
				System.out.println("Read write lock reading at "
					+System.currentTimeMillis()
					+" value"
					+map.get("foo"));
				sleep(1000);
			}
			catch(InterruptedException e)
			{
				e.printStackTrace();
			}
			finally
			{
				lock.readLock().unlock();
			}
		};

		executor.submit(readTask);
		executor.submit(readTask);

		stop(executor);
	}

	//Java 8 ships with a new kind of lock called StampedLock
	// which also support read and write locks just like ReadWriteLock.
	// In contrast to ReadWriteLock the locking methods of a StampedLock
	// return a stamp represented by a long value.
	// You can use these stamps to either release a lock or to check
	// if the lock is still valid.
	// Additionally stamped locks support
	// another lock mode called optimistic locking.

	//Stamped locks don't implement reentrant characteristics
	//Each call to lock returns a new stamp and
	// blocks if no lock is available
	// even if the same thread already holds a lock

	static void exampleStampedLock()
	{
		ExecutorService executor = Executors.newFixedThreadPool(2);
		Map<String, String> map = new HashMap<>();
		StampedLock lock = new StampedLock();

		executor.submit(() -> {
			long stamp = lock.writeLock();
			try
			{
				sleep(1000);
				map.put("foo", "bar");
				System.out.println("Stamped lock Writing");
			}
			catch(InterruptedException e)
			{
				e.printStackTrace();
			}
			finally
			{
				lock.unlockWrite(stamp);
			}
		});

		// Both read tasks wait untill the write lock is held
		// once the write lock is released then the read tasks are execduted

		Runnable readTask = () -> {
			long stamp = lock.readLock();
			try
			{
				System.out.println("Stamped lock reading at "
					+System.currentTimeMillis()
					+" value"
					+map.get("foo"));
				sleep(1000);
			}
			catch(InterruptedException e)
			{
				e.printStackTrace();
			}
			finally
			{
				lock.unlockRead(stamp);
			}
		};

		executor.submit(readTask);
		executor.submit(readTask);

		stop(executor);
	}

	//An optimistic read lock is acquired
	// by calling tryOptimisticRead() which
	// always returns a stamp without blocking the current thread,
	// no matter if the lock is actually available.
	// If there's already a write lock active the returned stamp equals zero.
	// You can always check if a stamp is valid by calling lock.validate(stamp).

	static void exampleStampedLockOptimistic()
	{
		ExecutorService executor = Executors.newFixedThreadPool(2);
		StampedLock lock = new StampedLock();

		executor.submit(() -> {
			long stamp = lock.tryOptimisticRead();
			try
			{
				System.out.println("Optimistic Lock Valid: "
					+lock.validate(stamp));
				sleep(1000);
				System.out.println("Optimistic Lock Valid: "
					+lock.validate(stamp));
				sleep(2000);
				System.out.println("Optimistic Lock Valid: "
					+lock.validate(stamp));
			}
			catch(InterruptedException e)
			{
				e.printStackTrace();
			}
			finally
			{
				lock.unlock(stamp);
			}
		});

		executor.submit(() -> {
			long stamp = lock.writeLock();
			try
			{
				System.out.println("Write Lock acquired");
				sleep(2000);
			}
			catch(InterruptedException e)
			{
				e.printStackTrace();
			}
			finally
			{
				lock.unlock(stamp);
				System.out.println("Write done");
			}
		});

		//The optimistic lock is valid right after acquiring the lock.
		// In contrast to normal read locks an optimistic lock doesn't
		// prevent other threads to obtain a write lock instantaneously.
		// After sending the first thread to sleep for one second the second thread
		// obtains a write lock without waiting for the optimistic read lock to be released.
		// From this point the optimistic read lock is no longer valid.
		// Even when the write lock is released the optimistic read locks stays invalid.

		executor.submit(() -> {
			long stamp = lock.tryOptimisticRead();
			try
			{
				System.out.println("Optimistic Lock Valid: after everything"
					+" "
					+lock.validate(stamp));
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			finally
			{
				lock.unlock(stamp);
			}
		});

		stop(executor);

	}

	static void exampleStampedReadToWriteLock()
	{
		ExecutorService executor = Executors.newFixedThreadPool(2);
		StampedLock lock = new StampedLock();

		Runnable readWriteTask = () -> {
			long stamp = lock.readLock();
			try
			{
				if(count
					== 0)
				{
					stamp = lock.tryConvertToWriteLock(stamp);
					if(stamp
						== 0L)
					{
						System.out.println("Could not convert to write lock");
						stamp = lock.writeLock();
					}
					count = 23;
				}
				System.out.println(count);
			}
			finally
			{
				lock.unlock(stamp);
			}
		};

		executor.submit(readWriteTask);
		executor.submit(readWriteTask);

	}

	//Whereas locks usually grant exclusive access to variables or resources,
	// a semaphore is capable of maintaining whole sets of permits.
	// This is useful in different scenarios where you have to limit
	// the amount concurrent access to certain parts of your application.

	static void exampleSemaphores()
	{
		ExecutorService executor = Executors.newFixedThreadPool(10);

		Semaphore semaphore = new Semaphore(5);

		Runnable longRunningTask = () -> {
			boolean permit = false;
			try
			{
				permit = semaphore.tryAcquire(1, TimeUnit.SECONDS);
				if(permit)
				{
					System.out.println("Semaphore acquired");
					sleep(5000);
				}
				else
				{
					System.out.println("Could not acquire semaphore");
				}
			}
			catch(InterruptedException e)
			{
				throw new IllegalStateException(e);
			}
			finally
			{
				if(permit)
				{
					semaphore.release();
				}
			}
		};

		IntStream.range(0, 10).forEach(i -> executor.submit(longRunningTask));

		stop(executor);
	}

	static void stop(ExecutorService executor)
	{
		try
		{
			executor.shutdown();
			executor.awaitTermination(60, TimeUnit.SECONDS);
		}
		catch(InterruptedException e)
		{
			System.err.println("termination interrupted");
		}
		finally
		{
			if(!executor.isTerminated())
			{
				System.err.println("killing non-finished tasks");
			}
			executor.shutdownNow();
		}
	}

}
