package Concurrency;

class DeadLockExample
{
	public static void main(String[] args) throws InterruptedException
	{
		Example example = new Example();

		Runnable first = () -> {
			example.run1();
		};

		Runnable second = () -> {
			example.run2();
		};

		Thread thread1 = new Thread(first);
		thread1.start();

		Thread thread2 = new Thread(second);
		thread2.start();

		thread1.join();
		thread2.join();
	}
}

class Example
{
	private Object key1 = new Object();
	private Object key2 = new Object();

	public void run1()
	{
		synchronized(key1)
		{
			System.out.println(Thread.currentThread().getName() + " I am in run 1");
			run2();
		}
	}

	public void run2()
	{
		synchronized(key2)
		{
			System.out.println(Thread.currentThread().getName() + " I am in run 2");
			run3();
		}
	}

	public void run3()
	{
		synchronized(key1)
		{
			System.out.println(Thread.currentThread().getName() + " I am in run 3");
		}
	}

}

