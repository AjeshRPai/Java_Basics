package DesignPatterns.Creation.AbstractFactory;


class CarFactory extends VehicleFactory
{

	@Override
	Vehicle getVehicle(VehicleType vehicleType)
	{
		switch(vehicleType)
		{
			case RACING:return new RacingCar();
			case ROAD:return new Sedan();
			default:return new Sedan();
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


