package DesignPatterns.Structural.Bridge;

import java.util.Arrays;
import java.util.List;

abstract class Shape
{
	protected Color color;

	//constructor with implementor as input argument
	public Shape(Color c)
	{
		this.color = c;
	}

	abstract public void applyColor();
}

interface Color
{
	public void applyColor();
}

class Triangle extends Shape
{

	public Triangle(Color c)
	{
		super(c);
	}

	@Override
	public void applyColor()
	{
		System.out.print("Triangle filled with color ");
		color.applyColor();
	}

}

class Pentagon extends Shape
{

	public Pentagon(Color c)
	{
		super(c);
	}

	@Override
	public void applyColor()
	{
		System.out.print("Pentagon filled with color ");
		color.applyColor();
	}

}

class RedColor implements Color
{

	public void applyColor()
	{
		System.out.println("red.");
	}
}

class GreenColor implements Color
{

	public void applyColor()
	{
		System.out.println("green.");
	}
}