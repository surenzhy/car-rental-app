package com.vehicle.rental.app.service.impl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vehicle.rental.app.bean.Distance;
import com.vehicle.rental.app.business.rules.RuleEngine;
import com.vehicle.rental.app.domain.Route;
import com.vehicle.rental.app.domain.Trip;
import com.vehicle.rental.app.domain.Vehicle;
import com.vehicle.rental.app.domain.VehicleType;
import com.vehicle.rental.app.service.ITripReservationService;
import com.vehicle.rental.app.util.RouteInformationHelper;

@Service
public class TripReservationServiceImpl implements ITripReservationService{

	@Autowired
	RuleEngine ruleEngine;
	@Autowired
	RouteInformationHelper helper;
	
	@PostConstruct
	public Double calculateTripExpense() {
		//validate input parameters
		VehicleType vt = new VehicleType("swift", "dezire", "BUS", "PETROL", false);
		Vehicle vehicle = new Vehicle(9, vt);
		Route r = new Route("Pune", "Mumbai");
		Trip trip = new Trip(vehicle, r, null, 8);
		Double finaRateForTrip = ruleEngine.getFinalRatePerKMForTrip(trip);
		Distance d = helper.getDistanceBetweenRoutes(r.getFromPlace(), r.getToPlace());
		System.out.println(finaRateForTrip);
		System.out.println(finaRateForTrip*d.getValue());
		return finaRateForTrip*d.getValue();
	}

	@Override
	public Integer calculateTripExpense(Trip trip) {
		// TODO Auto-generated method stub
		return null;
	}

}
