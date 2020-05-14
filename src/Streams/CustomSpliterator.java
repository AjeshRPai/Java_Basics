package Streams;

import java.util.ArrayList;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * You should almost certainly never have to deal with Spliterator as a user;
 * it should only be necessary if you're writing Collection types yourself and also intending to optimize parallelized operations on them.
 * For what it's worth, a Spliterator is a way of operating over the elements of a collection in a way that it's easy to split off part of the collection, e.g.
 * because you're parallelizing and want one thread to work on one part of the collection, one thread to work on another part, etc.
 *
 * You should essentially never be saving values of type Stream to a variable, either.
 * Stream is sort of like an Iterator, in that it's a one-time-use object that you'll almost always use in a fluent chain, as in the Javadoc example:
 *
 *
 *
 * Spliterator is introduced in Java 8 release in java.util package.
 * It supports Parallel Programming functionality.
 * We can use it for both Collection API and Stream API classes.
 * It provides characteristics about Collection or API objects.
 * We can NOT use this Iterator for Map implemented classes.
 * It uses tryAdvance() method to iterate elements individually in multiple Threads to support Parallel Processing.
 * It uses forEachRemaining() method to iterate elements sequentially in a single Thread.
 * It uses trySplit() method to divide itself into Sub-Spliterators to support Parallel Processing.
 * Spliterator supports both Sequential and Parallel processing of data.
 */

class PersonSpliterator implements Spliterator<Person>
{
	private final Spliterator<String> lineSpliterator;
	private String name;
	private int age;
	private String city;

	public PersonSpliterator(Spliterator<String> lineSpliterator)
	{
		this.lineSpliterator = lineSpliterator;
	}

	@Override
	public boolean tryAdvance(Consumer<? super Person> action)
	{
		if(this.lineSpliterator.tryAdvance(line -> this.name = line) &&
			this.lineSpliterator.tryAdvance(line -> this.age = Integer.parseInt(line)) &&
			this.lineSpliterator.tryAdvance(line -> this.city = line))
		{

			Person p = new Person(name, age, city);
			action.accept(p);
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public Spliterator<Person> trySplit()
	{
		return null;
	}

	@Override
	public long estimateSize()
	{
		return lineSpliterator.estimateSize() / 3;
	}

	@Override
	public int characteristics()
	{
		return lineSpliterator.characteristics();
	}
}

class Person
{
	private String name;
	private int age;
	private String city;

	public Person(String name, int age, String city)
	{
		this.name = name;
		this.age = age;
		this.city = city;
	}

	@Override
	public String toString()
	{
		return "Person{" +
			"name='" + name + '\'' +
			", age=" + age +
			", city='" + city + '\'' +
			'}';
	}
}

class CreatingSpliterator
{

	public static void main(String[] args)
	{

		List<String> names = new ArrayList<String>();

		names.add("Alice");
		names.add("52");
		names.add("New York");
		names.add("Brian");
		names.add("25");
		names.add("Chicago");
		names.add("Chelsea");
		names.add("19");
		names.add("London");
		names.add("David");
		names.add("44");
		names.add("Paris");
		names.add("Erica");
		names.add("32");
		names.add("Berlin");
		names.add("Francisco");
		names.add("64");
		names.add("Mexico");

		try(Stream<String> lines = names.stream())
		{

			Spliterator<String> lineSpliterator = lines.spliterator();
			Spliterator<Person> peopleSpliterator = new PersonSpliterator(lineSpliterator);

			Stream<Person> people = StreamSupport.stream(peopleSpliterator, false);
			people.forEach(System.out::println);

		}

	}
}
