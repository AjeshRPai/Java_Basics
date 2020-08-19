package FunctionalProgramming;

import java.util.*;
import java.util.function.*;

/**
 * https://github.com/Tandolf/tutorials/blob/master/src/main/java/se/andolf/tutorials/monads/Try.java
 * https://medium.com/swlh/write-a-monad-in-java-seriously-50a9047c9839
 */

class CustomMonad
{
	public static void main(String[] args)
	{

		int value = Try.ofThrowable(() -> Integer.valueOf("T")).orElse(5);

		System.out.println("value = " + value);

		int value2 = 0;
		try
		{
			value2 = Try.ofThrowable(() -> Integer.valueOf("1")).flatMap(n -> Try.ofThrowable(() -> (n * 2))).get();
		}
		catch(Throwable throwable)
		{
			throwable.printStackTrace();
		}

		System.out.println("value2 = " + value2);

	}
}

abstract class Try<T>
{

	//might throw an exception
	//takes in a supplier of function which means that the function produces something
	public static <U> Try<U> ofThrowable(Supplier<U> f)
	{
		Objects.requireNonNull(f);
		try
		{
			return successful(f.get());
		}
		catch(Throwable e)
		{
			return failure(e);
		}
	}

	public static <U> Success<U> successful(U u)
	{
		return new Success<>(u);
	}

	public static <U> Failure<U> failure(Throwable e)
	{
		return new Failure<>(e);
	}

	public abstract T orElse(T value);

	public abstract <U> Try<U> flatMap(Function<? super T, Try<U>> f);

	public abstract T get() throws Throwable;
}

class Success<T> extends Try<T>
{

	private final T value;

	Success(T value)
	{
		this.value = value;
	}

	@Override
	public T orElse(T value)
	{
		return this.value;
	}

	//Success.class
	@Override
	public <U> Try<U> flatMap(Function<? super T, Try<U>> f)
	{
		Objects.requireNonNull(f);
		return f.apply(value);
	}

	@Override
	public T get()
	{
		return value;
	}

}

class Failure<T> extends Try<T>
{
	private final Throwable e;

	Failure(Throwable e)
	{
		this.e = e;
	}

	@Override
	public T orElse(T value)
	{
		return value;
	}

	@Override
	public <U> Failure<U> flatMap(Function<? super T, Try<U>> f)
	{
		Objects.requireNonNull(f);
		return failure(e);
	}

	@Override
	public T get() throws Throwable
	{
		throw e;
	}
}









