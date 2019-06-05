package com.vehicle.rental.app.domain;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Trip {

	private String tripId;
	private Customer user;
	private Vehicle vehicle;
	private List<Route> route;
	private Date pickupDatetime;
	private Double totalCharge;
	private Integer totalPessangers;

	public Trip(Vehicle vehicle, List<Route> route, Customer user, Integer totalPessangers) {
		this.tripId = String.valueOf(UUID.randomUUID());
		this.user = user;
		this.vehicle = vehicle;
		this.totalPessangers = totalPessangers;
		this.route = route;
	}

	public Integer getTotalPessangers() {
		return totalPessangers;
	}

	public String getTripId() {
		return tripId;
	}

	public Customer getUser() {
		return user;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public List<Route> getRoute() {
		return route;
	}

	public Date getPickupDatetime() {
		return pickupDatetime;
	}

	public Double getTotalCharge() {
		return totalCharge;
	}
}
