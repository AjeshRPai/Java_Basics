package DesignPatterns.Structural.DecoratorPattern;

public class DecoratorPatternDemo
{
	public static void main(String[] args)
	{
		Food vegFood = new SauceDecorator(new CheeseDecorator(new VegFood()));

		System.out.println("vegFood = " + vegFood.make());

		Food nonVegFood = new SauceDecorator(new CheeseDecorator(new NonVegFood()));

		System.out.println("nonVegFood.make() = " + nonVegFood.make());

	}
}
