package DesignPatterns.Creation.AbstractFactory;

public class Usage
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
