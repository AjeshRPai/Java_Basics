package FunctionalProgramming;

import java.util.function.*;

public class ConsumerSupplierExample
{
	static BiFunction<Integer, Integer, Integer> addAndMultiply = (numberToIncrement, numberToMultiply) -> (numberToIncrement + 1) * numberToMultiply;

	//In java 8 the Bi function is a function
	// that takes two arguments and returns one result
	static Consumer<Customer> greetCustomer = customer -> System.out.println("Hello " + "" + customer.getCustomerName() + " " + "Thanks for registering with the Phone number " + customer.getCustomerPhoneNumber());
	static BiConsumer<Customer, Boolean> greetCustomerV2 = (customer, showPhoneNumber) -> System.out.println("Hello " + "" + customer.getCustomerName() + " " + "Thanks for registering with the Phone number " + (showPhoneNumber ? customer.getCustomerPhoneNumber() : "************"));

	// Consumer is functions that takes argument returns void
	// They consume the values supplied and only work with them
	static Supplier<String> generateSomeValue = () -> "This one is a default string";

	static int addAndMultiply(int number, int numberToMultiply)
	{
		return (number + 1) * numberToMultiply;
	}

	static void greetCustomer(Customer customer)
	{
		System.out.println("Hello " + "" + customer.getCustomerName() + " " + "Thanks for registering with the Phone number " + customer.getCustomerPhoneNumber());
	}

	// Supplier is anything that supplies a value
	// They generate the values and returns

	static void createAndGreetCustomer(String name, String phoneNumber, Boolean showPhoneNumber, Consumer<Customer> greetFunction)
	{
		Customer customer = new Customer(name, phoneNumber);
		if(showPhoneNumber)
			greetCustomer.accept(customer);
		else
			System.out.println("Hello " + customer.getCustomerName());
	}

	static String generateSomeValue()
	{
		return "This is a default value";
	}

	public static void main(String[] args)
	{
		System.out.println(addAndMultiply(4, 10));
		System.out.println(addAndMultiply.apply(4, 10));

		Customer john_doe = new Customer("John doe", "9999999999999");
		greetCustomer(john_doe);
		greetCustomer.accept(john_doe);
		greetCustomerV2.accept(john_doe, false);

		System.out.println(generateSomeValue());
		;
		System.out.println(generateSomeValue.get());
		;

		createAndGreetCustomer("John doe", "99999999999", false, greetCustomer);
	}

	static class Customer
	{
		private final String customerName;
		private final String customerPhoneNumber;

		public Customer(String customerName, String customerPhoneNumber)
		{
			this.customerName = customerName;
			this.customerPhoneNumber = customerPhoneNumber;
		}

		public String getCustomerName()
		{
			return customerName;
		}

		public String getCustomerPhoneNumber()
		{
			return customerPhoneNumber;
		}
	}

}

