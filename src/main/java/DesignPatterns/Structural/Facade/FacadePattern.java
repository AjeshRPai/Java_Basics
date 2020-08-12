package DesignPatterns.Structural.Facade;

class Waiter
{
	public void takeOrder()
	{
		System.out.println("takeOrder");
	}

	public void giveMenu()
	{
		System.out.println("give menu");
	}

	public void serveTheFood()
	{
		System.out.println("Serve the Food");
	}

	public void suggestDessert()
	{
		System.out.println("Suggest Dessert");
	}
}

class Cook
{

	public void getIngredients()
	{
		System.out.println("getIngredients");
	}

	public void prepareDish()
	{
		System.out.println("Cook");
	}

	public void addDressing()
	{
		System.out.println("add Dressing");
	}

	public void plateTheDish()
	{
		System.out.println("plate the Dish");
	}
}

class ProcessOrderFacade
{
	private Cook cook;

	private Waiter waiter;

	public ProcessOrderFacade(Cook cook, Waiter waiter)
	{
		this.cook = cook;
		this.waiter = waiter;
	}

	public ProcessOrderFacade()
	{
		this.cook = new Cook();
		this.waiter = new Waiter();
	}

	public void processOrder()
	{
		waiter.giveMenu();
		waiter.takeOrder();
		cook.getIngredients();
		cook.prepareDish();
		cook.addDressing();
		cook.plateTheDish();
		waiter.serveTheFood();
		waiter.suggestDessert();
	}

}