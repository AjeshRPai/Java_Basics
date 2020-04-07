package DesignPatterns.Creation;

/**
 * References
 * https://medium.com/@cancerian0684/singleton-design-pattern-and-how-to-make-it-thread-safe-b207c0e7e368
 * https://www.java67.com/2015/09/thread-safe-singleton-in-java-using-double-checked-locking-pattern.html
 * https://www.journaldev.com/171/thread-safety-in-java-singleton-classes-with-example-code
 * http://tutorials.jenkov.com/java-concurrency/volatile.html
 *
 * */

class Singleton
{
	private static volatile Singleton instance = null;

	private Singleton(){}

	public static Singleton getInstance()
	{
		if(instance == null)
		{
			synchronized(Singleton.class)
			{
				if(instance == null)
					instance = new Singleton();
			}
		}
		return instance;
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

