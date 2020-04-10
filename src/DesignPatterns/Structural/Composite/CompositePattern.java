package DesignPatterns.Structural.Composite;

import java.util.ArrayList;
import java.util.List;

interface Food
{
	public String cook();

	public int timeToMake();
}

class Meal implements Food
{
	public static StringBuffer compositeBuilder = new StringBuffer();

	private String timeOfDay;

	private List<Dish> dishes = new ArrayList<>();

	public Meal(String timeOfDay, int dishes)
	{
		this.timeOfDay = timeOfDay;
	}

	@Override
	public String cook()
	{
		for(Dish dish : dishes)
		{
			compositeBuilder.append(dish.cook());
			compositeBuilder.append("\n");
		}
		return compositeBuilder.toString();
	}

	@Override
	public int timeToMake()
	{
		for(Dish dish : dishes)
		{
			compositeBuilder.append(dish.timeToMake());
		}
		return Integer.parseInt(compositeBuilder.toString());
	}

	public void add(Dish dish)
	{
		dishes.add(dish);
	}
}

class Dish implements Food
{
	String name;

	String howToCook;

	int timeToMake;

	public Dish(String name, String howToCook, int timeToMake)
	{
		this.name = name;
		this.howToCook = howToCook;
		this.timeToMake = timeToMake;
	}

	@Override
	public String cook()
	{
		return howToCook;
	}

	@Override
	public int timeToMake()
	{
		return timeToMake;
	}

}

