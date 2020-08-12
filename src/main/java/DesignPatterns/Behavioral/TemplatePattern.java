package DesignPatterns.Behavioral;

//Template method design pattern is widely accepted behavioral design pattern to enforce some sort of algorithm (fixed set of steps) in the context of programming.
//It defines the sequential steps to execute a multi-step algorithm and optionally can provide a default implementation as well (based on requirements).

abstract class HouseTemplate
{

	//template method, final so subclasses can't override
	public final void buildHouse()
	{
		buildFoundation();
		buildPillars();
		buildWalls();
		buildWindows();
		System.out.println("House is built.");
	}

	//default implementation
	private void buildWindows()
	{
		System.out.println("Building Glass Windows");
	}

	//methods to be implemented by subclasses
	public abstract void buildWalls();

	public abstract void buildPillars();

	private void buildFoundation()
	{
		System.out.println("Building foundation with cement,iron rods and sand");
	}
}

class WoodenHouse extends HouseTemplate
{

	@Override
	public void buildWalls()
	{
		System.out.println("Building Wooden Walls");
	}

	@Override
	public void buildPillars()
	{
		System.out.println("Building Pillars with Wood coating");
	}

}

class GlassHouse extends HouseTemplate
{

	@Override
	public void buildWalls()
	{
		System.out.println("Building Glass Walls");
	}

	@Override
	public void buildPillars()
	{
		System.out.println("Building Pillars with glass coating");
	}

}

class HousingClient
{

	public static void main(String[] args)
	{

		HouseTemplate houseType = new WoodenHouse();

		//using template method
		houseType.buildHouse();
		System.out.println("************");

		houseType = new GlassHouse();

		houseType.buildHouse();
	}
}

//  Key Design Points
//    Mark template method as final so that implementing class can’t override and change the order of steps.
//    In base class, implement all the methods with default implementation so the derived classes need not to define them.
//    Mark all methods abstract which derived classes must implement.

//Popular implementations of template method pattern
//These are few popular existing implementations of template method design pattern.
//    Non-abstract methods of InputStream, OutputStream, Reader, Writer from Java IO.
//    Non-abstract methods of some abstract collection classes like AbstractList, AbstractSet, AbstractMap etc.