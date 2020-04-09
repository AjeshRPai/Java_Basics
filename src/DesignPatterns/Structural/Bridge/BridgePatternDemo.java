package DesignPatterns.Structural.Bridge;

public class BridgePatternDemo
{
	public static void main(String[] args)
	{
		Shape tri = new Triangle(new RedColor());
		tri.applyColor();

		Shape pent = new Pentagon(new GreenColor());
		pent.applyColor();
	}
}
