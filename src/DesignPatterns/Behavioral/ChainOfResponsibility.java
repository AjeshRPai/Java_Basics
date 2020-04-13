package DesignPatterns.Behavioral;

abstract class Handler
{
	protected Handler successor;

	public Handler getSuccessor()
	{
		return successor;
	}

	public void setSuccessor(Handler successor)
	{
		this.successor = successor;
	}

	abstract public void handleRequest(RequestType requestType);

}

enum RequestType
{
	HIGH_VALUE_PURCHASE,
	MEDIUM_VALUE_PURCHASE,
	LOW_VALUE_PURCHASE
}

class CEO extends Handler
{
	@Override
	public void handleRequest(RequestType requestType)
	{
		if(requestType == RequestType.HIGH_VALUE_PURCHASE)
		{
			System.out.println("CEO is handling the Request " + requestType);
		}
		else
		{
			successor.handleRequest(requestType);
		}
	}
}

class VP extends Handler
{

	@Override
	public void handleRequest(RequestType requestType)
	{
		if(requestType == RequestType.MEDIUM_VALUE_PURCHASE)
		{
			System.out.println("VP is handling the request " + requestType);
		}
		else
			successor.handleRequest(requestType);
	}
}

class Director extends Handler
{
	@Override
	public void handleRequest(RequestType requestType)
	{
		if(requestType == RequestType.LOW_VALUE_PURCHASE)
		{
			System.out.println("Director is handling the request " + requestType);
		}
		else
			successor.handleRequest(requestType);

	}
}

class ChainOfResponsibilityDemo
{
	public static void main(String[] args)
	{
		CEO ceo = new CEO();
		Director director = new Director();
		VP vp = new VP();

		ceo.setSuccessor(vp);
		vp.setSuccessor(director);

		director.handleRequest(RequestType.LOW_VALUE_PURCHASE);
		director.handleRequest(RequestType.HIGH_VALUE_PURCHASE);
		director.handleRequest(RequestType.MEDIUM_VALUE_PURCHASE);

	}
}