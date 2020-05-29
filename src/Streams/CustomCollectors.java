package Streams;

import java.util.Collections;
import java.util.Set;
import java.util.StringJoiner;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @param <T> T – the type of input elements to the reduction operation
 * @param <A> A – the mutable accumulation type of the reduction operation.
 *            *     The accumulator object type for keeping partial results during the collection process.
 * @param <R> R – the result type of the reduction operation. The actual return type of the collection process.
 */
interface CollectorBase<T, A, R>
{
	// Supplier that creates a new mutable result container.
	//This is used for creating the value of type A from T
	Supplier<A> supplier();

	// Function that folds a value into the result container.
	// This function consumes the value from supplier
	// This accumulates the value of Type  A and stores it
	BiConsumer<A, T> accumulator();

	// Merges two partial results.
	// In the case of Parallel processing this combines the different type A objects
	// Combines or merge
	BinaryOperator<A> combiner();

	// Perform the final transformation from the intermediate accumulation type
	// "A" to the final result type "R".
	Function<A, R> finisher();

	// Returns a Set of Collector.Characteristics indicating
	// Collectors characteristics.
	//  The characteristics of a Collector can be used to optimize the implementation of the reduction operation.
	//  Any combination of these three characteristics is possible:
	//
	//    Collector.Characteristics.CONCURRENT
	//    Indicates the accumulator objects support parallel or concurrent processing.
	//
	//    Collector.Characteristics.IDENTITY_FINISH
	//    Indicates the finisher function is the identity function so the accumulator might be cast directly in the result type.
	//
	//    Collector.Characteristics.UNORDERED
	//    Indicates the order of elements in the stream isn’t necessarily preserved.

	Set<java.util.stream.Collector.Characteristics> characteristics();

}

/***
 * https://medium.com/better-programming/java-stream-collectors-explained-6209a67a4c29
 */

class Joinector implements CollectorBase<CharSequence, StringJoiner, String>
{

	private final CharSequence delimiter;

	public Joinector(CharSequence delimiter)
	{
		this.delimiter = delimiter;
	}

	@Override
	public Supplier<StringJoiner> supplier()
	{
		// The accumulator object creation.
		return () -> new StringJoiner(this.delimiter);
	}

	@Override
	public BiConsumer<StringJoiner, CharSequence> accumulator()
	{
		// How to add new stream elements to the accumulator object.
		return StringJoiner::add;
	}

	@Override
	public BinaryOperator<StringJoiner> combiner()
	{
		// How to merge different accumulator objects together.
		return StringJoiner::merge;
	}

	@Override
	public Function<StringJoiner, String> finisher()
	{
		// How to extract the final result.
		return StringJoiner::toString;
	}

	@Override
	public Set<java.util.stream.Collector.Characteristics> characteristics()
	{
		// Special characteristics of our Collector.
		return Collections.emptySet();
	}
}






