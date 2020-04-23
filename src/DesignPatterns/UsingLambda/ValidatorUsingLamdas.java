package DesignPatterns.UsingLambda;

import java.util.function.Predicate;
import java.util.function.Supplier;

class PlayWithValidator
{

	public static void main(String[] args)
	{

		Person sarah = new Person("Sarah", 29);
		Person james = new Person(null, 29);
		Person mary = new Person("Mary", -10);
		Person john = new Person("John", 1_000);
		Person linda = new Person(null, 1_000);

		sarah =
			Validator.validate(p -> p.getName() != null, "The name should not be null")
				.on(sarah)
				.validate();
		System.out.println("Sarah : " + sarah);

		linda =
			Validator.validate(p -> p.getName() != null, "The name should not be null")
				.thenValidate(p -> p.getAge() > 0, "The age should be greater than 0")
				.thenValidate(p -> p.getAge() < 150, "The age should be lesser than 150")
				.on(linda)
				.validate();

		System.out.println("Linda : " + linda);

	}
}

interface Validator
{

	ValidatorSupplier on(Person p);

	default Validator thenValidate(Predicate<Person> predicate, String errorMessage)
	{
		return p -> {
			try
			{
				on(p).validate();
				if(predicate.test(p))
				{
					return () -> p;
				}
				else
				{
					return () -> {
						ValidationException exception = new ValidationException("The object is not valid");
						exception.addSuppressed(new IllegalArgumentException(errorMessage));
						throw exception;
					};
				}
			}
			catch(ValidationException validationException)
			{
				if(predicate.test(p))
				{
					return () -> {
						throw validationException;
					};
				}
				else
				{
					validationException.addSuppressed(new IllegalArgumentException(errorMessage));
					return () -> {
						throw validationException;
					};
				}
			}
		};
	}

	static Validator validate(Predicate<Person> predicate, String errorMessage)
	{
		return p -> {
			if(predicate.test(p))
			{
				return () -> p;
			}
			else
			{
				return () -> {
					ValidationException exception = new ValidationException("The object is not valid");
					exception.addSuppressed(new IllegalArgumentException(errorMessage));
					throw exception;
				};
			}
		};
	}

	interface ValidatorSupplier extends Supplier<Person>
	{
		default Person validate()
		{
			return get();
		}
	}

	static class ValidationException extends RuntimeException
	{
		public ValidationException(String errorMessage)
		{
			super(errorMessage);
		}
	}
}

class Person
{

	private String name;
	private int age;

	public Person(String name, int age)
	{
		this.name = name;
		this.age = age;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public int getAge()
	{
		return age;
	}

	public void setAge(int age)
	{
		this.age = age;
	}

	@Override
	public String toString()
	{
		return "Person [name=" + name + ", age=" + age + "]";
	}
}