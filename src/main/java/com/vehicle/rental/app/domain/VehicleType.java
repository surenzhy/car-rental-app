package com.vehicle.rental.app.domain;

public class VehicleType {
	public String company;
	public String model;
	public String type;
	public boolean ac;
	public String fuelType;

	public VehicleType(String company, String model, String type, String fuelType) {
		this.company = company;
		this.model = model;
		this.type = type;
		this.ac = false;
		this.fuelType = fuelType;
	}

	public VehicleType(String company, String model, String type, String fuelType, boolean isAC) {
		this.company = company;
		this.model = model;
		this.type = type;
		this.ac = isAC;
		this.fuelType = fuelType;
	}

	public String getCompany() {
		return company;
	}

	public String getModel() {
		return model;
	}

	public String getType() {
		return type;
	}

	public boolean isAC() {
		return ac;
	}

	public String getFuelType() {
		return fuelType;
	}
	
	

}