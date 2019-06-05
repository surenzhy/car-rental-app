package com.vehicle.rental.app.domain;

public final class Route {

	private String fromPlace;
	private String toPlace;
	public Route(String fromPlace, String toPlace) {
		this.fromPlace = fromPlace;
		this.toPlace = toPlace;
	}

	public String getFromPlace() {
		return fromPlace;
	}

	public String getToPlace() {
		return toPlace;
	}

}
