package DesignPatterns.Creation.Factory;

class CarFactory
{
	public static Car getCar(CarType carType)
	{
		switch(carType)
		{
			case SPORTS:return new SportsCar();
			case OFFROAD:return new SUV();
			default:return new SportsCar();
		}
	}
}


