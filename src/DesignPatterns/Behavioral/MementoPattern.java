package DesignPatterns.Behavioral;

//A memento is is like a restore point during the life cycle on the object,
// which the client application can use to restore the object state to its state.
// Conceptually, it is very much like we create restore points for operating systems and use to restore the system if something breaks or system crashes.

//The memento pattern has three participants.
//
//    Originator – is the object that knows how to create and save it’s state for future.
//    It provides methods createMemento() and restore(memento).
//
//    Caretaker – performs an operation on the Originator while having the possibility to rollback.
//    It keeps track of multiple mementos.
//    Caretaker class refers to the Originator class for saving (createMemento()) and restoring (restore(memento)) originator’s internal state.
//
//    Memento – the lock box that is written and read by the Originator, and shepherded by the Caretaker.
//    In principle, a memento must be in immutable object so that no one can change it’s state once created.

import java.util.ArrayList;
import java.util.List;

//Memento
class Article
{
	String id;
	String title;
	String content;

	public Article(String id, String title, String content)
	{
		this.id = id;
		this.title = title;
		this.content = content;
	}

	@Override
	public String toString()
	{
		return "Article{" +
			"id='" + id + '\'' +
			", title='" + title + '\'' +
			", content='" + content + '\'' +
			'}';
	}

	public static Article CreateArticle(String id, String title, String content)
	{
		return new Article(id, title, content);
	}
}

//Originator
class Originator
{
	private String content;

	public void setState(String content)
	{
		this.content = content;
	}

	public String getState()
	{
		return content;
	}

	public Article saveStateToMemento(Article article)
	{
		return Article.CreateArticle(article.id, article.title, content);
	}

	public void getStateFromMemento(Article article)
	{
		content = article.content;
	}
}

class CareTaker
{
	private List<Article> mementoList = new ArrayList<Article>();

	public void add(Article state)
	{
		mementoList.add(state);
	}

	public Article get(int index)
	{
		return mementoList.get(index);
	}

	@Override
	public String toString()
	{
		return "CareTaker{" +
			"mementoList=" + mementoList +
			'}';
	}
}

class MementoPatternDemo
{
	public static void main(String[] args)
	{

		Originator originator = new Originator();
		CareTaker careTaker = new CareTaker();

		Article article = new Article("1", "Memento", "Starting");

		originator.setState("Starting");

		//save state
		careTaker.add(originator.saveStateToMemento(article));

		originator.setState("Started");

		//save state
		careTaker.add(originator.saveStateToMemento(article));

		originator.setState("Dont save this state");
		System.out.println("Current State: " + originator.getState());

		originator.setState("Middle");
		//save state
		careTaker.add(originator.saveStateToMemento(article));
		System.out.println("Current State: " + originator.getState());

		originator.getStateFromMemento(careTaker.get(0));
		System.out.println("First saved State: " + originator.getState());
		originator.getStateFromMemento(careTaker.get(1));
		System.out.println("Second saved State: " + originator.getState());

		System.out.println("careTaker = " + careTaker);
	}
}




