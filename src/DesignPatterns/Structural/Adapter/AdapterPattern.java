package DesignPatterns.Structural.Adapter;

class Person
{
	private String firstName;

	private String lastName;

	private String gender;

	private String age;

	public Person(String firstName, String lastName, String gender, String age)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.age = age;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public String getGender()
	{
		return gender;
	}

	public String getAge()
	{
		return age;
	}

	@Override
	public String toString()
	{
		return "Person{" +
			"firstName='" + firstName + '\'' +
			", lastName='" + lastName + '\'' +
			", gender='" + gender + '\'' +
			", age='" + age + '\'' +
			'}';
	}
}

interface User
{
	public String getName();

	public String getGender();

	public Integer getAge();

}

class Adapter implements User
{

	private Person person;

	public Adapter(Person person)
	{
		this.person = person;
	}

	@Override
	public String getName()
	{
		return person.getFirstName() + " " + person.getLastName();
	}

	@Override
	public String getGender()
	{
		return person.getGender();
	}

	@Override
	public Integer getAge()
	{
		return Integer.parseInt(person.getAge());
	}

	@Override
	public String toString()
	{
		return "Adapter{" +
			"person=" + person +
			'}';
	}
}
