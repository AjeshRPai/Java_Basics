package Concurrency;

class RaceConditionExample
{
	public static void main(String[] args) throws InterruptedException
	{
		LongWrapper longWrapper = new LongWrapper(0L);

		Runnable runnable = new Runnable()
		{
			@Override
			public void run()
			{
				for(int i = 0; i < 1_000; i++)
				{
					longWrapper.incrementValue();
				}
			}
		};

		Thread[] threads = new Thread[1_000];

		for(int i = 0; i < threads.length; i++)
		{
			threads[i] = new Thread(runnable);
			threads[i].start();
		}

		for(int i = 0; i < threads.length; i++)
		{
			threads[i].join();
		}

		//The result should 1_000 * 1_000 which is 1000000
		//If the key and Synchronized and the key is deleted, Then race condition will occur
		System.out.println("long wrapper value " + longWrapper.getValue());
	}
}

class LongWrapper
{
	//To Avoid Race Condition
	private Object key = new Object();
	private Long l;

	public LongWrapper(Long l)
	{
		this.l = l;
	}

	public Long getValue()
	{
		return l;
	}

	public void incrementValue()
	{
		//To avoid race condition
		synchronized(key)
		{
			l = l + 1;
		}
	}
}
