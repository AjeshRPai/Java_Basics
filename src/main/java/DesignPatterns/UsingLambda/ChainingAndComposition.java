package DesignPatterns.UsingLambda;

import java.util.*;

//Difference between Composition and Chanining is that the Chaining takes Functions and Parameters
// Composing takes only the Functions

@FunctionalInterface
interface ConsumerDesign<T>
{
	void accept(T t);

	default ConsumerDesign<T> andThen(ConsumerDesign<T> other)
	{
		Objects.requireNonNull(other);
		return (T t) -> {
			other.accept(t);
			this.accept(t);
		};
	}
}

interface Function<P, R>
{
	R convert(P p);

	default <T> Function<P, T> andThen(Function<R, T> after)
	{
		Objects.requireNonNull(after);
		return (P p) -> {
			R r = this.convert(p);
			return after.convert(r);
		};
	}

	default <V> Function<V, R> compose(Function<V, P> other)
	{
		Objects.requireNonNull(other);
		return (V v) -> {
			P p = other.convert(v);
			return this.convert(p);
		};
	}
}

class Example
{
	int Something;

	public Example(int something)
	{
		Something = something;
	}

	public int getSomething()
	{
		return Something;
	}

	public void setSomething(int something)
	{
		Something = something;
	}
}

class ConsumerDemo
{
	public static void main(String[] args)
	{
		ChainingExample();
		ComposingExample();

	}

	private static void ComposingExample()
	{
		Example meteo = new Example(10);
		int i = 10;
		Function<Example, Integer> readCelsius = m -> m.getSomething();
		Function<Integer, Double> celsiusToFahrenheit = t -> t * 9d / 5d + 32d;

		Function<Example, Double> readFahrenheit = readCelsius.andThen(celsiusToFahrenheit);
		readFahrenheit = celsiusToFahrenheit.compose(readCelsius);

		System.out.println("Meteo is F " + readFahrenheit.convert(meteo));
	}

	private static void ChainingExample()
	{
		ConsumerDesign<String> c1 = s -> System.out.println("c1 = " + s);
		ConsumerDesign<String> c2 = s -> System.out.println("C2 = " + s);
		ConsumerDesign<String> c3 = c1.andThen(c2);
		c3.accept("Hello");
	}
}