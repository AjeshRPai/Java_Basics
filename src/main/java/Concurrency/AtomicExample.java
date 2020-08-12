package Concurrency;

import java.util.concurrent.*;
import java.util.concurrent.atomic.*;
import java.util.stream.*;

public class AtomicExample
{

	//the atomic classes make heavy use of compare-and-swap (CAS),
	// an atomic instruction directly supported by most modern CPUs.
	// Those instructions usually are much faster than synchronizing via locks.

	public static void main(String[] args)
	{
		AtomicInteger atomicInt = new AtomicInteger(0);

		ExecutorService executor = Executors.newFixedThreadPool(2);

		IntStream.range(0, 1000).forEach(i -> executor.submit(atomicInt::incrementAndGet));

		stop(executor);

		System.out.println(atomicInt.get());
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
