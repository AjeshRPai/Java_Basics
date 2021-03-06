package Concurrency;

import java.util.concurrent.atomic.*;

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
					longWrapper.incrementAtomicVariable();
					longWrapper.incrementVolatile();
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
		System.out.println("volatile long wrapper value " + longWrapper.getVolatile());
		System.out.println("atomic long wrapper value " + longWrapper.getAtomicVariable());
	}
}

class LongWrapper
{
	//To Avoid Race Condition
	private Object key = new Object();
	private Long l;
	private volatile Long m = 0L;
	private AtomicLong n = new AtomicLong(0L);

	public LongWrapper(Long l)
	{
		this.l = l;
	}

	public Long getValue()
	{
		synchronized(key)
		{
			return l;
		}
	}

	public void incrementValue()
	{
		//To avoid race condition
		synchronized(key)
		{
			l = l + 1;
		}
	}

	public Long getVolatile()
	{
		return m;
	}

	public void incrementVolatile()
	{
		m = m + 1;
	}

	public AtomicLong getAtomicVariable()
	{
		return n;
	}

	public void incrementAtomicVariable()
	{
		n.incrementAndGet();
	}
}
