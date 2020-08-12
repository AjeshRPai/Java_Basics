package DesignPatterns.UsingLambda;


import java.util.*;
import java.util.function.Function;
import java.util.function.*;

interface Builder<T>
{
	void register(String label, Factory<T> factory);
}

interface Factory<T> extends Supplier<T>
{
	default T newInstance()
	{
		return get();
	}
}

interface Registry<T>
{

	Factory<? extends T> buildShapeFactory(String shape);

	public static <T> Registry<T> createRegistry(Consumer<Builder<T>> consumer, Function<String, Factory<T>> errorFunction)
	{

		Map<String, Factory<T>> map = new HashMap<>();
		Builder<T> builder = (label, factory) -> map.put(label, factory);
		consumer.accept(builder);

		return shape -> map.computeIfAbsent(shape, errorFunction);
	}
}
class PlayWithRegistryBuilder
{

	@SuppressWarnings("unchecked")
	public static void main(String[] args)
	{

		Consumer<Builder<Shape>> consumer1 =
			builder -> builder.register("rectangle", Rectangle::new);
		Consumer<Builder<Shape>> consumer2 =
			builder -> builder.register("triangle", Triangle::new);
		Consumer<Builder<Shape>> consumer3 =
			builder -> builder.register("square", Square::new);

		Consumer<Builder<Shape>> initializer = consumer1.andThen(consumer2).andThen(consumer3);

		Registry<Shape> registry = Registry.createRegistry(
			initializer, s -> {
				throw new IllegalArgumentException("Unknown shape: " + s);
			});

		Factory<Rectangle> buildRectangleFactory =
			(Factory<Rectangle>) registry.buildShapeFactory("rectangle");

		Rectangle rectangle = buildRectangleFactory.newInstance();
		System.out.println("Rectangle = " + rectangle);

		Factory<Triangle> buildTriangleFactory =
			(Factory<Triangle>) registry.buildShapeFactory("triangle");

		Triangle triangle = buildTriangleFactory.newInstance();
		System.out.println("Triangle = " + triangle);

		Factory<Square> buildSquareFactory =
			(Factory<Square>) registry.buildShapeFactory("square");

		Square square = buildSquareFactory.newInstance();
		System.out.println("square = " + square);
	}
}

abstract class Shape
{
}

class Rectangle extends Shape
{

	@Override
	public String toString()
	{
		return "Rectangle []";
	}

}

class Square extends Shape
{

	@Override
	public String toString()
	{
		return "Square []";
	}
}

class Triangle extends Shape
{

	@Override
	public String toString()
	{
		return "Triangle []";
	}
}



