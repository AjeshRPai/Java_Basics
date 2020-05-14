package Streams;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamLazinessExample
{

	/**
	 * Employee class
	 **/

	private static class Employee
	{

		int id;

		String name;

		public Employee(int id, String name)
		{

			this.id = id;

			this.name = name;

		}

		public String getName()
		{

			return this.name;

		}

	}

	public static void main(String[] args) throws InterruptedException
	{

		List<Employee> employees = new ArrayList<>();

		/** Creating the employee list **/

		for(int i = 1; i < 10000000; i++)
		{

			employees.add(new StreamLazinessExample.Employee(i, "name_" + i));

		}

		/** Only Intermediate Operations; it will just create another streams and
		 * will not perform any operations **/

		Stream<String> employeeNameStreams = employees.parallelStream().filter(employee -> employee.id % 2 == 0)

			.map(employee -> {

				System.out.println("In Map - " + employee.getName());

				return employee.getName();

			});

		/** Adding some delay to make sure nothing has happen till now **/

		Thread.sleep(2000);

		//This will be printed first as the IN Map will printed last when terminal operation is called
		System.out.println("2 sec");

		/** Terminal operation on the stream and it will invoke the Intermediate Operations
		 * filter and map **/

		employeeNameStreams.collect(Collectors.toList());

	}

}