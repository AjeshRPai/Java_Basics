package DesignPatterns.Behavioral;

//This pattern is based on Open/closed principle.
// We don’t need to modify the context [closed for modification], but can choose and add any implementation [open for extension].
//For example, in Collections.sort() – we don’t need to change the sort method to achieve different sorting results.
// We can just supply different comparators in runtime.

interface Operation
{
	int execute(int a, int b);
}

class Addition implements Operation
{
	@Override
	public int execute(int a, int b)
	{
		return a + b;
	}
}

class Multiplication implements Operation
{
	@Override
	public int execute(int a, int b)
	{
		return a * b;
	}
}

class Division implements Operation
{
	@Override
	public int execute(int a, int b)
	{
		return a / b;
	}
}

class OperationContext
{
	private Operation operation;

	public void setOperation(Operation operation)
	{
		this.operation = operation;
	}

	public int executeStrategy(int a, int b)
	{
		return operation.execute(a, b);
	}
}

class Demo
{
	public static void main(String[] args)
	{
		int a = 10;
		int b = 5;

		OperationContext operationContext = new OperationContext();

		operationContext.setOperation(new Multiplication());
		System.out.println("Multiplication = " + operationContext.executeStrategy(a, b));

		operationContext.setOperation(new Addition());
		System.out.println("Addition = " + operationContext.executeStrategy(a, b));

		operationContext.setOperation(new Division());
		System.out.println("Division = " + operationContext.executeStrategy(a, b));

	}
}

//State can be considered as an extension of Strategy.
// Both patterns are based on composition: they change the behavior of the context by delegating some work to helper objects.
// Strategy makes these objects completely independent and unaware of each other.
// However, State doesn’t restrict dependencies between concrete states, letting them alter the state of the context at will.