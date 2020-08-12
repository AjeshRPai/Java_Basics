package Concurrency;

import java.util.concurrent.*;

// Callables are functional interfaces just like
// runnables but instead of being void they return a value.

public class CallableExamples
{
	public static void main(String[] args)
	{
		Callable<Integer> task = () -> {
			try
			{
				TimeUnit.SECONDS.sleep(1);
				return 123;
			}
			catch(InterruptedException e)
			{
				throw new IllegalStateException("task interrupted", e);
			}
		};
		// executor returns a special result of type Future
		// which can be used to retrieve the actual result at a later point in time.

		ExecutorService executor = Executors.newFixedThreadPool(1);
		Future<Integer> future = executor.submit(task);

		System.out.println("Future is done or not "
			+future.isDone());

		try
		{
			Integer result = future.get();
			System.out.println("Again checking Future is done or not "
				+future.isDone());
			System.out.println("result = "
				+result);
		}
		catch(InterruptedException | ExecutionException e)
		{
			e.printStackTrace();
		}
	}
}
