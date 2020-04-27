package Concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceExample
{
	public static class Task implements java.lang.Runnable
	{
		@Override
		public void run()
		{
			System.out.println("Print something in executor service");
		}
	}

	public static void main(String[] args) throws InterruptedException
	{
		ExecutorService executorService = Executors.newScheduledThreadPool(10);
		executorService.execute(new Task());
		executorService.awaitTermination(10000, TimeUnit.MILLISECONDS);
		executorService.shutdownNow();
	}
}
