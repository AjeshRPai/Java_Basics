package FunctionalProgramming;

//    Functions accept arguments and produce results.
//    Operators produce results of the same type as their arguments (special case of function).
//    Predicates return boolean values and accept any arguments (boolean-valued function).
//    Suppliers return values and accept nothing.
//    Consumers accept arguments and return no result.

import java.util.function.*;

class FunctionalInterfaceExample
{

	public static void main(String[] args)
	{
		// generator accepts nothing and returns integer value 3
		IntSupplier generator = () -> 3;

		final int factor = 2;

		// multiplier accepts an integer value and returns another one, it uses closure
		IntUnaryOperator multiplier = val -> factor * val;

		// predicate isEven accepts an integer value and returns true if the value is even else false
		IntPredicate isEven = val -> val % 2 == 0;

		// printer accepts a value and prints it in the standard out, it returns nothing
		Consumer<String> printer = System.out::println;

		System.out.println(generator.getAsInt());

		System.out.println(multiplier.applyAsInt(2));

		System.out.println((isEven.test(13)));

		printer.accept("hello my name");

		// Since Java 8, any collection has the forEach() method. The method accepts Consumer and calls it for each element in the collection.

	}
}