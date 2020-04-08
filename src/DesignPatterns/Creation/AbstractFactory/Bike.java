package DesignPatterns.Creation.AbstractFactory;

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

class BikeFactory extends VehicleFactory
{
	@Override
	Vehicle getVehicle(VehicleType vehicleType)
	{
		switch(vehicleType)
		{
			case ROAD:return new RoadBike();
			case RACING:return new RacingBike();
			default:return new RoadBike();
		}
	}
}
