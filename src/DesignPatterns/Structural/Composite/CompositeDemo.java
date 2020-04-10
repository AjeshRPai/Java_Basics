package DesignPatterns.Structural.Composite;

public class CompositeDemo
{
	public static void main(String[] args)
	{
		Meal meal = new Meal("Morning", 3);

		Dish dish = new Dish("Coffee", "Brew", 3);

		Dish dish1 = new Dish("Sandwich", "Toast", 10);

		Dish dish2 = new Dish("Eggs", "Poach", 7);

		meal.add(dish);
		meal.add(dish1);
		meal.add(dish2);

		System.out.println(dish.cook());

		System.out.println(meal.cook());

	}
}
