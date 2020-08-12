package DesignPatterns.Structural.Proxy;

public class ProxyPatternDemo
{
	public static void main(String[] args)
	{
		ExpensiveObject object = new ExpensiveObjectProxy();
		object.process();
		object.process();

		//Dynamic proxy demo

		Task task = (Task) MyInvocationHandler.newInstance(new TaskImpl());
		task.setData("Test");
		System.out.println(task.getIntData(5));
	}
}
