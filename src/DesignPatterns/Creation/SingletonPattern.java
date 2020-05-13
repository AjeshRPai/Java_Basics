package DesignPatterns.Creation;

/**
 * References
 * https://medium.com/@cancerian0684/singleton-design-pattern-and-how-to-make-it-thread-safe-b207c0e7e368
 * https://www.java67.com/2015/09/thread-safe-singleton-in-java-using-double-checked-locking-pattern.html
 * https://www.journaldev.com/171/thread-safety-in-java-singleton-classes-with-example-code
 * http://tutorials.jenkov.com/java-concurrency/volatile.html
 * https://javarevisited.blogspot.com/2014/05/double-checked-locking-on-singleton-in-java.html#ixzz6MIrFbSIZ
 */

class Singleton
{

	private volatile static Singleton _instance;

	private Singleton()
	{
		// preventing Singleton object instantiation from outside
	}

	/*
	 * 1st version: creates multiple instance if two thread access
	 * this method simultaneously
	 */

	public static Singleton getInstance()
	{
		if(_instance == null)
		{
			_instance = new Singleton();
		}
		return _instance;
	}

	/*
	 * 2nd version : this definitely thread-safe and only
	 * creates one instance of Singleton on concurrent environment
	 * but unnecessarily expensive due to cost of synchronization
	 * at every call.
	 */

	public static synchronized Singleton getInstanceTS()
	{
		if(_instance == null)
		{
			_instance = new Singleton();
		}
		return _instance;
	}

    /*
     * 3rd version : An implementation of double checked locking of Singleton.
     * Intention is to minimize cost of synchronization and  improve performance,
     * by only locking critical section of code, the code which creates
 instance of Singleton class.
     * By the way this is still broken, if we don't make _instance volatile,
 as another thread can
     * see a half initialized instance of Singleton.
     */

	public static Singleton getInstanceDC()
	{
		if(_instance == null)
		{
			synchronized(Singleton.class)
			{
				if(_instance == null)
				{
					_instance = new Singleton();
				}
			}
		}
		return _instance;
	}
}

class SingletonUsage
{
	public static void main(String[] args)
	{
		Singleton singleton = Singleton.getInstance();

		System.out.println("singleton = " + singleton);

		System.out.println(System.currentTimeMillis());

		Singleton singleton1 = Singleton.getInstance();

		System.out.println("singleton1 = " + singleton1);

	}
}

