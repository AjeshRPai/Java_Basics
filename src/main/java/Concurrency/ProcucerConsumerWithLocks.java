package Concurrency;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;

//IF there are exceptions on the lock ,
// Without await the consumers will not be able to consume and it will be stuck forever
// With await it will be shutdown

//Its used for deadlock condition where error will be thrown

public class ProcucerConsumerWithLocks
{

	public static void main(String[] args) throws InterruptedException
	{

		List<Integer> buffer = new ArrayList<>();

		Lock lock = new ReentrantLock();
		Condition isEmpty = lock.newCondition();
		Condition isFull = lock.newCondition();

		class Consumer implements Callable<String>
		{

			public String call() throws InterruptedException, TimeoutException
			{
				int count = 0;
				while(count++ < 50)
				{
					try
					{
						lock.lock();
						while(isEmpty(buffer))
						{
							// wait
							if(!isEmpty.await(10, TimeUnit.MILLISECONDS))
							{
								throw new TimeoutException("Consumer time out");
							}
						}
						buffer.remove(buffer.size() - 1);
						// signal
						isFull.signalAll();
					}
					finally
					{
						lock.unlock();
					}
				}
				return "Consumed " + (count - 1);
			}
		}

		class Producer implements Callable<String>
		{

			public String call() throws InterruptedException
			{
				int count = 0;
				while(count++ < 50)
				{
					try
					{
						lock.lock();
						int i = 10 / 0;
						while(isFull(buffer))
						{
							// wait
							isFull.await();
						}
						buffer.add(1);
						// signal
						isEmpty.signalAll();
					}
					finally
					{
						lock.unlock();
					}
				}
				return "Produced " + (count - 1);
			}
		}

		List<Producer> producers = new ArrayList<>();
		for(int i = 0; i < 4; i++)
		{
			producers.add(new Producer());
		}

		List<Consumer> consumers = new ArrayList<>();
		for(int i = 0; i < 4; i++)
		{
			consumers.add(new Consumer());
		}

		System.out.println("Producers and Consumers launched");

		List<Callable<String>> producersAndConsumers = new ArrayList<>();
		producersAndConsumers.addAll(producers);
		producersAndConsumers.addAll(consumers);

		ExecutorService executorService = Executors.newFixedThreadPool(8);
		try
		{
			List<Future<String>> futures = executorService.invokeAll(producersAndConsumers);

			futures.forEach(
				future -> {
					try
					{
						System.out.println(future.get());
					}
					catch(InterruptedException | ExecutionException e)
					{
						System.out.println("Exception: " + e.getMessage());
					}
				});

		}
		finally
		{
			executorService.shutdown();
			System.out.println("Executor service shut down");
		}

	}

	public static boolean isEmpty(List<Integer> buffer)
	{
		return buffer.size() == 0;
	}

	public static boolean isFull(List<Integer> buffer)
	{
		return buffer.size() == 10;
	}
}
