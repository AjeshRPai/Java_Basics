package DesignPatterns.Structural.Facade;

public class FacadePatternDemo
{
	public static void main(String[] args)
	{

		Cook cook = new Cook();
		Waiter waiter = new Waiter();

		//below is the process of taking order
		//Before using facade pattern
		/*waiter.giveMenu();
		waiter.takeOrder();
		cook.getIngredients();
		cook.prepareDish();
		cook.addDressing();
		cook.plateTheDish();
		waiter.serveTheFood();
		waiter.suggestDessert();*/

		//After using facade pattern

		ProcessOrderFacade processOrderFacade = new ProcessOrderFacade(cook, waiter);
		processOrderFacade.processOrder();

	}
}
