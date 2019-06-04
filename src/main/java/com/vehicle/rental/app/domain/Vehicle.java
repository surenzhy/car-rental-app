package com.vehicle.rental.app.domain;

public class Vehicle {

	private int id;

	private VehicleType vehicleType;

	private Integer seatingCapacity;

	private boolean available;

	public Vehicle(Integer seatingCapacity, VehicleType vehicleType) {
		this.vehicleType = vehicleType;
		this.seatingCapacity = seatingCapacity;
		this.available = false;
	}

	public int getId() {
		return id;
	}

	public VehicleType getVehicleType() {
		return vehicleType;
	}

	public Integer getSeatingCapacity() {
		return seatingCapacity;
	}

	public boolean isAvailable() {
		return available;
	}

}
