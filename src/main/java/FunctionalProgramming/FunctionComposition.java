package FunctionalProgramming;

import java.util.*;
import java.util.function.*;

public class FunctionComposition
{
	public static void main(String[] args)
	{
		Function<Integer, Integer> adder = x -> x + 10;
		Function<Integer, Integer> multiplier = x -> x * 5;

		// compose: adder(multiplier(5))
		System.out.println("result: " + adder.compose(multiplier).apply(5));

		// andThen: multiplier(adder(5))
		System.out.println("result: " + adder.andThen(multiplier).apply(5));

		//Generally, f.compose(g).apply(x) is the same as f(g(x)), and f.andThen(g).apply(x) is the same as g(f(x)).

		List<Integer> numbers = new ArrayList<>();
		for(int i = 0; i <= 30; i++)
			numbers.add(i);

		//Composing predicates
		IntPredicate isEven = x -> x % 2 == 0;
		IntPredicate dividedBy3 = x -> x % 3 == 0;
		IntPredicate pred = isEven.negate().or(dividedBy3);

		// print all odd values and even values that can be divided by 3.
		numbers.forEach(val -> {
			if(pred.test(val))
				System.out.print(val + " ");
		});
	}
}
