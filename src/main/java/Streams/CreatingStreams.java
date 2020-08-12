package Streams;

import java.math.*;
import java.util.*;
import java.util.stream.*;

public class CreatingStreams
{
	public static void main(String[] args)
	{
		//1) The most common way to take a stream is to create it from a collection. Any collection has a method stream() for this purpose.

		List<Integer> famousNumbers = Arrays.asList(0,
			1,
			1,
			2,
			3,
			5,
			8,
			13,
			21,
			34,
			55);
		Stream<Integer> numbersStream = famousNumbers.stream();

		Set<String> usefulConcepts = new HashSet<>(Arrays.asList("functions",
			"lazy",
			"immutability"));
		Stream<String> conceptsStream = usefulConcepts.stream();

		//2) From an array

		Stream<Double> doubleStream = Arrays.stream(new Double[] {1.01, 1d, 0.99, 1.02, 1d, 0.99});

		//3) From a string

		IntStream stream = "aibohphobia".chars(); // It returns IntStream!

		//4) From values

		LongStream longStream = LongStream.of(111_111_111L,
			333_333_333L);

		//5) From another streams

		Stream<BigInteger> concatedStream = Stream.concat(Stream.empty(),
			Stream.empty());

		//6) Use Stream.generate()

		Stream<User> userStream = Stream.generate(User::new);
		DoubleStream randomStream = DoubleStream.generate(Math::random);

		//7) Use Stream.iterate()

		IntStream oddNumbersStream = IntStream.iterate(1,
			x -> x + 2);

		//8) Use Stream.range() or Stream.rangeClosed(). Method rangeClosed() includes an upper bound.

		LongStream rangedStream = LongStream.rangeClosed(100_000,
			1_000_000);

	}

	static class User
	{
		String firstName;
		String lastName;
	}
}
