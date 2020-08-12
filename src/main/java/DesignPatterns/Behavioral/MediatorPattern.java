package DesignPatterns.Behavioral;

//  Mediator design pattern is one of the behavioral design pattern,
// so it deals with the behaviors of objects.
// Mediator design pattern is used to provide a centralized communication medium between different objects in a system.

/*
  Real world example of mediator pattern
      A great real world example of mediator pattern is traffic control room at airports.
      If all flights will have to interact with each other for finding which flight is going to land next, it will create a big mess.
      Rather flights only send their status to the tower.
      These towers in turn send the signals to conform which airplane can take-off or land.
      We must note that these towers do not control the whole flight. They only enforce constraints in the terminal areas.

      Another good example of mediator pattern is a chat application.
      In a chat application we can have several participants.
      It’s not a good idea to connect each participant to all the others because the number of connections would be really high.
      The best solution is to have a hub where all participants will connect; this hub is just the mediator class.


     In Java programming, the execute() method inside the java.util.concurrent.Executor interface follows this pattern.
     The different overloaded versions of various schedule() methods of the java.util.Timer class also can be considered to follow this pattern.
 */

import java.util.*;

interface ChatMediator
{

	public void sendMessage(String msg, User user);

	void addUser(User user);
}

abstract class User
{
	protected ChatMediator mediator;
	protected String name;

	public User(ChatMediator med, String name)
	{
		this.mediator = med;
		this.name = name;
	}

	public abstract void send(String msg);

	public abstract void receive(String msg);
}

class UserImpl extends User
{

	public UserImpl(ChatMediator med, String name)
	{
		super(med, name);
	}

	@Override
	public void send(String msg)
	{
		System.out.println(this.name + ": Sending Message=" + msg);
		mediator.sendMessage(msg, this);
	}

	@Override
	public void receive(String msg)
	{
		System.out.println(this.name + ": Received Message:" + msg);
	}

}

class ChatMediatorImpl implements ChatMediator
{

	private List<User> users;

	public ChatMediatorImpl()
	{
		this.users = new ArrayList<>();
	}

	@Override
	public void addUser(User user)
	{
		this.users.add(user);
	}

	@Override
	public void sendMessage(String msg, User user)
	{
		for(User u : this.users)
		{
			//message should not be received by the user sending it
			if(u != user)
			{
				u.receive(msg);
			}
		}
	}
}

class ChatClient
{

	public static void main(String[] args)
	{
		ChatMediator mediator = new ChatMediatorImpl();
		User user1 = new UserImpl(mediator, "Pankaj");
		User user2 = new UserImpl(mediator, "Lisa");
		User user3 = new UserImpl(mediator, "Saurabh");
		User user4 = new UserImpl(mediator, "David");
		mediator.addUser(user1);
		mediator.addUser(user2);
		mediator.addUser(user3);
		mediator.addUser(user4);

		user1.send("Hi All");

	}
}

//Reference
//1.https://www.journaldev.com/1730/mediator-design-pattern-java


