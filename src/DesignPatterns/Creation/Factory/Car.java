package DesignPatterns.Creation.Factory;

abstract class Car
{
	protected String engine = "1.1l";
	protected String fuel = "Petrol";
	protected String tyres = "4";
	protected String seats = "4";

	abstract Car createCar();

	@Override
	public String toString()
	{
		return "Car{" +
			"engine='" + engine + '\'' +
			", fuel='" + fuel + '\'' +
			", tyres='" + tyres + '\'' +
			", seats='" + seats + '\'' +
			'}';
	}

	public Car()
	{
		this.createCar();
	}

	public String getEngine()
	{
		return engine;
	}

	public String getFuel()
	{
		return fuel;
	}

	public String getTyres()
	{
		return tyres;
	}

	public String getSeats()
	{
		return seats;
	}

	public void setEngine(String engine)
	{
		this.engine = engine;
	}

	public void setFuel(String fuel)
	{
		this.fuel = fuel;
	}

	public void setTyres(String tyres)
	{
		this.tyres = tyres;
	}

	public void setSeats(String seats)
	{
		this.seats = seats;
	}
}

class SportsCar extends Car
{
	@Override
	Car createCar()
	{
		setEngine("1.2l");
		return this;
	}
}

class SUV extends Car
{

	@Override
	Car createCar()
	{
		setTyres("6");
		return this;
	}
}
