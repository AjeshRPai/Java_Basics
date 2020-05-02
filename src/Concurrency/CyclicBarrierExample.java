package Concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CyclicBarrierExample
{
	public static void main(String[] args)
	{

		class Friend implements Callable<String>
		{

			private CyclicBarrier barrier;

			public Friend(CyclicBarrier barrier)
			{
				this.barrier = barrier;
			}

			public String call() throws Exception
			{

				try
				{
					Random random = new Random();
					Thread.sleep((random.nextInt(20) * 100 + 100));
					System.out.println("I just arrived, waiting for the others...");

					//Step 2
					//IF the thread dont join with a time out then the Tasks will be executed
					// . Threads will be released and then the other threads can compute
					//barrier.await(5,TimeUnit.SECONDS);

					//Step 1
					barrier.await();

					System.out.println("Let's go to the cinema!");
					return "ok";
				}
				catch(InterruptedException e)
				{
					System.out.println("Interrupted");
				}
				return "nok";
			}
		}

		//Step 1
		ExecutorService executorService = Executors.newFixedThreadPool(4);

		//Step 2
		//IF the no of threads is less than the Cyclic barrier parties then the Cyclic barriers wont start processing
		//ExecutorService executorService = Executors.newFixedThreadPool(2);

		CyclicBarrier barrier = new CyclicBarrier(4, () -> System.out.println("Barrier openning"));
		List<Future<String>> futures = new ArrayList<>();

		try
		{
			for(int i = 0; i < 4; i++)
			{
				Friend friend = new Friend(barrier);
				futures.add(executorService.submit(friend));
			}

			futures.forEach(
				//Step 1 & 2
				future ->
				{
					try
					{
						future.get();
					}
					catch(InterruptedException | ExecutionException e)
					{
						e.printStackTrace();
					}
				}

				//Step 3
				/*future -> {
					try {
						future.get(200, TimeUnit.MILLISECONDS);
					} catch (InterruptedException | ExecutionException e) {
						System.out.println(e.getMessage());
					} catch (TimeoutException e) {
						System.out.println("Timed out");
						future.cancel(true);
					}
				}*/
			);

		}
		finally
		{
			executorService.shutdown();
		}
	}
}
