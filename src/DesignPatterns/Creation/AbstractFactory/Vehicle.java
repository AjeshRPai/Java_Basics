package DesignPatterns.Creation.AbstractFactory;

abstract class Vehicle
{
	protected String engine;
	protected String fuel;
	protected String tyres;
	protected String seats;

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

enum VehicleType
{
	ROAD,RACING
}
