package DesignPatterns.Creation.AbstractFactory;

public abstract class VehicleFactory
{
	public static VehicleFactory getFactory(int no_of_wheels)
	{
		switch(no_of_wheels)
		{
			case 4: return new CarFactory();
			case 2:return new BikeFactory();
			default:return new BikeFactory();
		}
	}

	abstract Vehicle getVehicle(VehicleType vehicleType);
}
