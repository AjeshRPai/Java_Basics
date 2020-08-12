package DesignPatterns.Creation;

abstract class VehicleFactory
{
	public static VehicleFactory getFactory(int no_of_wheels)
	{
		switch(no_of_wheels)
		{
			case 4:
				return new CarFactory();
			case 2:
				return new BikeFactory();
			default:
				return new BikeFactory();
		}
	}

	abstract Vehicle getVehicle(VehicleType vehicleType);
}

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
	ROAD,
	RACING
}

class CarFactory extends VehicleFactory
{

	@Override
	Vehicle getVehicle(VehicleType vehicleType)
	{
		switch(vehicleType)
		{
			case RACING:
				return new RacingCar();
			case ROAD:
				return new Sedan();
			default:
				return new Sedan();
		}
	}
}

class Sedan extends Vehicle
{
	public Sedan()
	{
		this.setEngine("1.2L");
		this.setSeats("5");
		this.setFuel("Diesel");
		this.setTyres("4");
	}

}

class RacingCar extends Vehicle
{
	public RacingCar()
	{
		this.setEngine("1.8L");
		this.setSeats("2");
		this.setFuel("Petrol");
		this.setTyres("4");
	}
}

class BikeFactory extends VehicleFactory
{
	@Override
	Vehicle getVehicle(VehicleType vehicleType)
	{
		switch(vehicleType)
		{
			case ROAD:
				return new RoadBike();
			case RACING:
				return new RacingBike();
			default:
				return new RoadBike();
		}
	}
}

class RoadBike extends Vehicle
{
	public RoadBike()
	{
		this.setEngine(".5L");
		this.setSeats("2");
		this.setFuel("Petrol");
		this.setTyres("2");
	}
}

class RacingBike extends Vehicle
{
	public RacingBike()
	{
		this.setEngine("1.1L");
		this.setSeats("1");
		this.setFuel("Petrol+");
		this.setTyres("2");
	}

}

class Usage
{
	public static void main(String[] args)
	{
		VehicleFactory carFactory = VehicleFactory.getFactory(4);
		Vehicle racingCar = carFactory.getVehicle(VehicleType.RACING);
		System.out.println("racingCar = " + racingCar);

		Vehicle roadCar = carFactory.getVehicle(VehicleType.ROAD);
		System.out.println("roadCar = " + roadCar);

		VehicleFactory bikeFactory = VehicleFactory.getFactory(2);
		Vehicle racingBike = bikeFactory.getVehicle(VehicleType.RACING);
		System.out.println("racingBike = " + racingBike);

		Vehicle roadBike = bikeFactory.getVehicle(VehicleType.ROAD);
		System.out.println("roadBike = " + roadBike);

	}
}