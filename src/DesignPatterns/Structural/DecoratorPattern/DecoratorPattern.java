package DesignPatterns.Structural.DecoratorPattern;

interface Food
{
	public String make();
}

class VegFood implements Food
{
	@Override
	public String make()
	{
		return "Vegetarian Food";
	}
}

class NonVegFood implements Food
{
	@Override
	public String make()
	{
		return "Non vegetarian food";
	}
}

abstract class FoodDecorator implements Food
{
	protected Food food;

	public FoodDecorator(Food food)
	{
		this.food = food;
	}

	@Override
	public String make()
	{
		return food.make();
	}
}

class CheeseDecorator extends FoodDecorator
{

	public CheeseDecorator(Food food)
	{
		super(food);
	}

	@Override
	public String make()
	{
		return super.make() + " add cheese";
	}
}

class SauceDecorator extends FoodDecorator
{
	public SauceDecorator(Food food)
	{
		super(food);
	}

	@Override
	public String make()
	{
		return super.make() + " add Sauce";
	}
}


