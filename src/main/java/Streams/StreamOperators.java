package Streams;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class StreamOperators
{
	public static void main(String[] args)
	{
		Employee[] arrayOfEmps = {
			new Employee(1,
				"Jeff Bezos",
				100000.0),
			new Employee(2,
				"Bill Gates",
				200000.0),
			new Employee(3,
				"Mark Zuckerberg",
				300000.0)
		};

		List<Employee> empList = Arrays.asList(arrayOfEmps);

		//sort based on name
		//Takes in a comparator
		List<Employee> employees = empList.stream()
			.sorted((e1, e2) -> e1.getName().compareTo(e2.getName()))
			.collect(Collectors.toList());

		System.out.println("employees = " + employees);

		Employee firstEmp = empList.stream()
			.min((e1, e2) -> e1.getId() - e2.getId())
			.orElseThrow(NoSuchElementException::new);

		System.out.println("firstEmp = " + firstEmp);

		Employee lastemployee = empList.stream()
			.max((e1, e2) -> e1.getId() - e2.getId())
			.orElseThrow(NoSuchElementException::new);

		System.out.println("lastemployee = " + lastemployee);

		List<Integer> intList = Arrays.asList(2,
			5,
			3,
			2,
			4,
			3,
			6,
			9,
			12,
			10,
			9,
			10,
			21,
			22,
			26,
			30);
		List<Integer> distinctIntList =
			intList.stream().distinct().collect(Collectors.toList());

		System.out.println("distinctIntList = " + distinctIntList);

		boolean allEven = intList.stream().allMatch(i -> i % 2 == 0);
		System.out.println("allEven = " + allEven);

		boolean oneEven = intList.stream().anyMatch(i -> i % 2 == 0);
		System.out.println("oneEven = " + oneEven);

		boolean noneMultipleOfThree = intList.stream().noneMatch(i -> i % 3 == 0);
		System.out.println("noneMultipleOfThree = " + noneMultipleOfThree);

		//Reducing operators

		Double sumSal = empList.stream()
			.map(Employee::getSalary)
			.reduce(0.0,
				Double::sum);
		System.out.println("sumSal = " + sumSal);

		String empNames = empList.stream()
			.map(Employee::getName)
			.collect(Collectors.joining(", "))
			.toString();
		System.out.println("empNames = " + empNames);

		Set<String> empNamesSet = empList.stream()
			.map(Employee::getName)
			.collect(Collectors.toSet());

		System.out.println("empNamesSet = " + empNamesSet);

		Vector<String> empNamesCollection = empList.stream()
			.map(Employee::getName)
			.collect(Collectors.toCollection(Vector::new));

		DoubleSummaryStatistics stats = empList.stream()
			.collect(Collectors.summarizingDouble(Employee::getSalary));

		DoubleSummaryStatistics statsDouble = empList.stream()
			.mapToDouble(Employee::getSalary)
			.summaryStatistics();

		Map<Boolean, List<Integer>> isEven = intList.stream().collect(
			Collectors.partitioningBy(i -> i % 2 == 0));

		IntPredicate predicate1 = value -> value % 2 == 0;
		IntPredicate predicate2 = value -> value % 3 == 0;
		IntPredicate predicate3 = value -> value % 5 == 0;

		Collection<IntPredicate> predicates =
			Arrays.asList(value -> value % 2 == 0,
				value -> value % 3 == 0,
				value -> value % 5 == 0);

		//can also be written as
		Collection<IntPredicate> predicates2 =
			Arrays.asList(predicate1,
				predicate2,
				predicate3);

		IntStream.rangeClosed(1,
			100).forEachOrdered(i -> {
			System.out.print(
				i + ": " + predicate1.test(i) + " " + predicate2.test(i) + " " + predicate3.test(i) + " -> ");
			System.out.println(negateEachAndConjunctAll(predicates).test(i));
		});

	}

	public static IntPredicate negateEachAndConjunctAll(Collection<IntPredicate> predicates)
	{
		return predicates.stream()
			.map(IntPredicate::negate)
			.reduce(n -> true,
				IntPredicate::and);
	}

	private static class Employee
	{
		public int id;
		public String name;
		public Double salary;

		public Employee(int id,
			String name,
			double salary)
		{
			this.id = id;
			this.name = name;
			this.salary = salary;
		}

		public int getId()
		{
			return id;
		}

		public String getName()
		{
			return name;
		}

		public Double getSalary()
		{
			return salary;
		}

		@Override
		public String toString()
		{
			return "Employee{" +
				"id=" + id +
				", name='" + name + '\'' +
				", salary=" + salary +
				'}';
		}
	}
}
