package com.vehicle.rental.app.domain;

import java.util.Date;
import java.util.UUID;

public class Trip {

	private String tripId;
	private User user;
	private Vehicle vehicle;
	private Route route;
	private Date pickupDatetime;
	private Double totalCharge;
	private Integer totalPessangers;

	public Trip(Vehicle vehicle, Route route, User user, Integer totalPessangers) {
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

	public User getUser() {
		return user;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public Route getRoute() {
		return route;
	}

	public Date getPickupDatetime() {
		return pickupDatetime;
	}

	public Double getTotalCharge() {
		return totalCharge;
	}
}
