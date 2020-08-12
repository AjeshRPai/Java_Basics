package Streams;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class ParallelStreams
{
	/**
	 * Size of data. The more bigger size of data => the greater speedup.
	 *
	 * Boxing/Unboxing. Primitive values are processed faster than boxed values.
	 *
	 * The number of cores are available at runtime. The more available cores => the greater speedup.
	 *
	 * Cost per element processing. The longer each element is processed => the more efficient parallelization.
	 * But it is not recommended to use parallel stream for performing too long operations (for example, network interconnection).
	 *
	 * Source data structure. Usually initial data source is a collection.
	 * The easier it is splited into parts => the greater speedup.
	 * For example, ArrayList, arrays and IntStream.range() constructor are good sources for data splitting because they support random access.
	 * Other, such as LinkedList, Stream.iterate are bad sources for data splitting.
	 *
	 * Type of operations: stateless or stateful.
	 * Stateless operations (examples: filter, map) are better for parallel processing than stateful operations (examples: distinct, sorted, limit).﻿
	 */

	public static void main(String[] args)
	{
		Function<Long, Long> multiplyByone = x -> x * 2;

		Function<Integer, Integer> multiply = x -> x * 2;

		List<Integer> sortedNumbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

		sortedNumbers.parallelStream().map(multiply::apply).forEachOrdered(n -> System.out.print(n + " "));

		sortedNumbers.stream().map(multiply::apply).forEachOrdered(n -> System.out.print(n + " "));

		long[] numbersParallel = LongStream.rangeClosed(0, 100).parallel().map(multiplyByone::apply).toArray();

		System.out.println("numbers = " + Arrays.toString(numbersParallel));

		long[] numbersSequential = LongStream.rangeClosed(0, 100).map(multiplyByone::apply).toArray();

		//out put can be different and un ordered in parallel cases

		System.out.println("numbersSequential = " + Arrays.toString(numbersSequential));

	}
}
