package FunctionalProgramming;

import java.util.function.BiFunction;
import java.util.function.Function;

public class LambdaExpressions
{
	// a simple way to write a lambda expression
	// BiFunction<T, U, R>
	// T and U are the Arguments and R is the Result
	static BiFunction<String, String, String> fullName = (firstName, lastName) -> firstName + lastName;

	// if the only one argument
	// Function<T,U>
	// T and U are the Parameters
	static Function<Integer, Integer> identity = x -> x; // (x) -> x; is valid too

	// without type inference
	static Function<Integer, Integer> mult = (Integer x) -> x * 2;

	// with multiple statements
	static Function<Integer, Integer> adder = (x) -> {
		x += 5;
		x += 10;
		return x;
	};

	public static void main(String[] args)
	{
		String firstName = "Jane";
		String lastName = "Smith";

		System.out.println(" full Name " + fullName.apply(firstName,
			lastName));
	}

}
