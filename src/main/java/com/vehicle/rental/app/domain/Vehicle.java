package com.vehicle.rental.app.domain;

import org.springframework.beans.factory.annotation.Autowired;

import com.vehicle.rental.app.config.IEnv;

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
