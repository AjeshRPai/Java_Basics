package DesignPatterns.Creation;

import java.util.*;

abstract class Item implements Cloneable
{
	private String title;
	private double price;
	private String url;

	@Override
	protected Object clone() throws CloneNotSupportedException
	{
		return super.clone();
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public double getPrice()
	{
		return price;
	}

	public void setPrice(double price)
	{
		this.price = price;
	}

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	@Override
	public String toString()
	{
		return "Item{" +
			"title='" + title + '\'' +
			", price=" + price +
			", url='" + url + '\'' +
			'}';
	}
}

class Movie extends Item
{
	private String runtime;

	public String getRuntime()
	{
		return runtime;
	}

	public void setRuntime(String runtime)
	{
		this.runtime = runtime;
	}

	@Override
	public String toString()
	{
		return "Movie{" +
			"runtime='" + runtime + '\'' + ", "+ super.toString()+
			'}';
	}
}

class Registry
{
	private Map<String,Item> items =new HashMap<String, Item>();

	public Registry()
	{
		loadItems();
	}

	private void loadItems()
	{
		Movie movie = new Movie();
		movie.setRuntime("2 hours");
		movie.setPrice(120);
		movie.setTitle("Default title");
		movie.setUrl("www.interstellar.com");
		items.put("movie",movie);
	}

	public Item createItem(String type)
	{
		Item item = null;
		try
		{
			item = (Item)(items.get(type).clone());
		}
		catch(CloneNotSupportedException e)
		{
			e.printStackTrace();
		}
		return item;
	}


}

class ProtoTypeUsage
{
	public static void main(String[] args)
	{
		Registry registry = new Registry();
		Movie dunkirk = (Movie) registry.createItem("movie");
		dunkirk.setTitle("Dunkirk");

		System.out.println("dunkirk = " + dunkirk.toString());

		Movie darknight = (Movie) registry.createItem("movie");
		darknight.setTitle("Darknight");
		darknight.setPrice(130);

		System.out.println("darknight = " + darknight.toString());

	}

}
