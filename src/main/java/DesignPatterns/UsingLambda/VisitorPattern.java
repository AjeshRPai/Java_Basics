package DesignPatterns.UsingLambda;

import java.util.*;
import java.util.function.Function;
import java.util.function.*;

class PlayWithVisitor
{

	public static void main(String[] args)
	{

		Car car = new Car();
		Engine engine = new Engine();
		Body body = new Body();

		Consumer<VisitorBuilder<String>> consumer =
			Visitor.<Car, String> forType(Car.class).execute((Car c) -> "Visiting car: " + c)
				.forType(Engine.class).execute((Engine e) -> "Visiting engine: " + e)
				.forType(Body.class).execute((Body b) -> "Visiting body: " + b);

		Visitor<String> visitor = Visitor.of(consumer);

		String visitedEngine = visitor.visit(engine);
		System.out.println("Engine: " + visitedEngine);

		String visitedCar = visitor.visit(car);
		System.out.println("Car: " + visitedCar);

		String visitedBody = visitor.visit(body);
		System.out.println("Body: " + visitedBody);
	}
}

interface Visitor<R>
{

	R visit(Object o);

	static <R> Visitor<R> of(Consumer<VisitorBuilder<R>> consumer)
	{
		Map<Class<?>, java.util.function.Function<Object, R>> registry = new HashMap<>();
		VisitorBuilder<R> visitorBuilder = new VisitorBuilder<R>()
		{

			@Override
			public <T> void register(Class<T> type, java.util.function.Function<T, R> function)
			{
				registry.put(type, function.compose(type::cast));
			}
		};
		consumer.accept(visitorBuilder);
		System.out.println("Registry: " + registry.keySet());
		return o -> registry.get(o.getClass()).apply(o);
	}

	static <T, R> X<T, R> forType(Class<T> type)
	{
		return () -> type;
	}

	interface X<T, R>
	{

		Class<T> type();

		default Y<R> execute(java.util.function.Function<T, R> function)
		{
			return visitorBuilder -> visitorBuilder.register(type(), function);
		}
	}

	interface Y<R> extends Consumer<VisitorBuilder<R>>
	{

		default <T> Z<T, R> forType(Class<T> type)
		{
			return index -> index == 0 ? this : type;
		}

		default Y<R> andThen(Y<R> after)
		{
			return t -> {
				this.accept(t);
				after.accept(t);
			};
		}
	}

	interface Z<T, R>
	{

		Object get(int index);

		default Class<T> type()
		{
			return (Class<T>) get(1);
		}

		default Y<R> previousConsumer()
		{
			return (Y<R>) get(0);
		}

		default Y<R> execute(Function<T, R> function)
		{
			return previousConsumer().andThen(
				visitorBuilder -> visitorBuilder.register(type(), function));
		}
	}
}

interface VisitorBuilder<R>
{

	<T> void register(Class<T> type, Function<T, R> function);
}

class Body
{

	@Override
	public String toString()
	{
		return "Body []";
	}
}

class Car
{

	@Override
	public String toString()
	{
		return "Body []";
	}
}

class Engine
{

	@Override
	public String toString()
	{
		return "Body []";
	}
}