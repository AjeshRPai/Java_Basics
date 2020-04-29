package Concurrency;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorServiceExample
{

	public static void main(String[] args) throws InterruptedException, ExecutionException
	{
		Runnable task = () -> System.out.println("I am in thread " + Thread.currentThread().getName());

		// ExecutorService service = Executors.newSingleThreadExecutor();
		ExecutorService service = Executors.newFixedThreadPool(4);

		for(int i = 0; i < 10; i++)
		{
			// This will create a new thread
			// new Thread(task).start();
			service.execute(task);
		}

		service.shutdown();

		Callable<String> callableTask = () -> {
			throw new IllegalStateException("I throw an exception in thread " + Thread.currentThread().getName());
		};

		Callable<String> callableTask2 = () -> {
			return Thread.currentThread().getName();
		};

		ExecutorService executor = Executors.newFixedThreadPool(4);

		try
		{
			for(int i = 0; i < 10; i++)
			{
				Future<String> future = executor.submit(callableTask2);
				System.out.println("I get: " + future.get());
			}
		}
		finally
		{
			executor.shutdown();
		}

		try
		{
			for(int i = 0; i < 10; i++)
			{
				Future<String> future = executor.submit(callableTask);
				System.out.println("I get: " + future.get());
			}
		}
		finally
		{
			executor.shutdown();
		}
	}
}
