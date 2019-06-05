package com.vehicle.rental.app.domain;

public class Vehicle {

	private int id;

	private VehicleType vehicleType;

	private Integer seatingCapacity;

	private boolean available;

	public Vehicle(VehicleType vehicleType, Integer seatingCapacity) {
		this.vehicleType = vehicleType;
		this.seatingCapacity = seatingCapacity;
		this.available = false;
	}


	public VehicleType getVehicleType() {
		return vehicleType;
	}

	public Integer getSeatingCapacity() {
		return seatingCapacity;
	}


}
