package Concurrency;

public class RunnableExample
{
	public static void main(String[] args)
	{
		Runnable runnable = () -> System.out.println("Running in the thread, " + Thread.currentThread().getName());

		Thread t1 = new Thread(runnable);
		t1.setName("Implemented thread");

		t1.start();

		//The below will run in the Main thread itself
		t1.run();

	}
}
