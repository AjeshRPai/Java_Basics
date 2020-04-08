package DesignPatterns.Creation.Factory;

class Demo
{
	public static void main(String[] args)
	{
		Car car = CarFactory.getCar(CarType.SPORTS);
		System.out.println("car = " + car);

		Car car1 = CarFactory.getCar(CarType.OFFROAD);
		System.out.println("car1 = " + car1);
	}
}
