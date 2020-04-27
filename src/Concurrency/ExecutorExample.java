package Concurrency;

import java.util.concurrent.Executor;

public class ExecutorExample
{
	public static void main(String[] args)
	{
		Invoker invoker = new Invoker();
		invoker.execute(() -> {
			System.out.println("Something just trying to do");
		});
	}

	public static class Invoker implements Executor
	{
		@Override
		public void execute(java.lang.Runnable command)
		{
			command.run();

		}
	}
}

