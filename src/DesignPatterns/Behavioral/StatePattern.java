package DesignPatterns.Behavioral;

//The state pattern solves problems where an object should change its behavior when its internal state changes.
// Also, adding new states should not affect the behavior of existing states.

interface State
{
	public void doAction();
}

class TVStartState implements State
{

	@Override
	public void doAction()
	{
		System.out.println("TV is turned ON");
	}

}

class TVStopState implements State
{

	@Override
	public void doAction()
	{
		System.out.println("TV is turned OFF");
	}

}

class TVContext implements State
{

	private State tvState;

	public void setState(State state)
	{
		this.tvState = state;
	}

	public State getState()
	{
		return this.tvState;
	}

	@Override
	public void doAction()
	{
		this.tvState.doAction();
	}

}

class TVRemote
{

	public static void main(String[] args)
	{
		TVContext context = new TVContext();
		State tvStartState = new TVStartState();
		State tvStopState = new TVStopState();

		context.setState(tvStartState);
		context.doAction();

		context.setState(tvStopState);
		context.doAction();

	}

}


