package DesignPatterns.Structural.Proxy;

import java.lang.reflect.*;

interface Task
{
	public void setData(String data);

	public int getIntData(int x);

}

class TaskImpl implements Task
{
	@Override
	public void setData(String data)
	{
		System.out.println("data = " + data + " is being saved");
	}

	@Override
	public int getIntData(int x)
	{
		return x + 10;
	}

}

class MyInvocationHandler implements InvocationHandler
{

	public static Object newInstance(Object ob)
	{
		return Proxy.newProxyInstance(ob.getClass().getClassLoader(),
			new Class<?>[] {Task.class}, new MyInvocationHandler(ob));
	}

	private Object obj;

	public MyInvocationHandler(Object obj)
	{
		this.obj = obj;
	}

	public Object invoke(Object proxy, Method m, Object[] args) throws Throwable
	{
		Object result;
		try
		{
			if(m.getName().indexOf("get") > -1)
			{
				System.out.println("...get Method Executing...");
			}
			else
			{
				System.out.println("...set Method Executing...");
			}
			result = m.invoke(obj, args);
		}
		catch(InvocationTargetException e)
		{
			throw e;
		}
		catch(Exception e)
		{
			throw e;
		}
		return result;
	}
}


