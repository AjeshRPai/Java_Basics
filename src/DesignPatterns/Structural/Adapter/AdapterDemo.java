package DesignPatterns.Structural.Adapter;

import sun.nio.cs.US_ASCII;

import java.util.ArrayList;
import java.util.List;

public class AdapterDemo
{
	public static void main(String[] args)
	{
		List<User> users = new ArrayList<User>();

		Person person = new Person("Ajesh", "Pai", "M", "29");

		users.add(new Adapter(person));

		System.out.println("users = " + users.toString());

	}
}
