package DesignPatterns.Behavioral;

//In observer pattern, there are many observers (subscriber objects) that are observing a particular subject (publisher object).
// Observers register themselves to a subject to get a notification when there is a change made inside that subject.

import java.util.ArrayList;
import java.util.List;

interface Subject
{
	public void attach(Observer o);

	public void detach(Observer o);

	public void notifyUpdate(Message m);
}

class MessagePublisher implements Subject
{
	private List<Observer> observers = new ArrayList<>();

	@Override
	public void attach(Observer o)
	{
		observers.add(o);
	}

	@Override
	public void detach(Observer o)
	{
		observers.remove(o);
	}

	public void notifyUpdate(Message m)
	{
		for(Observer o : observers)
		{
			o.update(m);
		}
	}
}

interface Observer
{
	public void update(Message m);
}

class MessageSubscriberOne implements Observer
{

	@Override
	public void update(Message m)
	{
		System.out.println("MessageSubscriberOne :: " + m.getMessageContent());
	}
}

class MessageSubscriberTwo implements Observer
{

	@Override
	public void update(Message m)
	{
		System.out.println("MessageSubscriberTwo :: " + m.getMessageContent());
	}
}

class Message
{

	final String messageContent;

	public Message(String m)
	{
		this.messageContent = m;
	}

	public String getMessageContent()
	{
		return messageContent;
	}
}

class Main
{

	public static void main(String[] args)
	{
		MessageSubscriberOne s1 = new MessageSubscriberOne();
		MessageSubscriberTwo s2 = new MessageSubscriberTwo();

		MessagePublisher p = new MessagePublisher();

		p.attach(s1);
		p.attach(s2);

		p.notifyUpdate(new Message("First Message"));

		p.detach(s1);

		p.notifyUpdate(new Message("Second Message")); //s2  will receive the update
	}
}




