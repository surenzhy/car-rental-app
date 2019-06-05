package com.vehicle.rental.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vehicle.rental.app.bean.Distance;
import com.vehicle.rental.app.business.rules.IRuleEngine;
import com.vehicle.rental.app.domain.Route;
import com.vehicle.rental.app.domain.Trip;
import com.vehicle.rental.app.service.ITripReservationService;
import com.vehicle.rental.app.util.RouteInformationHelper;

@Service
public class TripReservationServiceImpl implements ITripReservationService{

	@Autowired
	IRuleEngine ruleEngine;
	@Autowired
	RouteInformationHelper helper;
	
	@Override
	public Double calculateTripExpense(Trip trip) {
		Double finaRateForTrip = ruleEngine.getFinalRatePerKMForTrip(trip);
		Double finalCost = 0.0;
		if(null != trip && null != trip.getRoute()) {
			for(Route ru : trip.getRoute()) {
				Distance d = helper.getDistanceBetweenRoutes(ru.getFromPlace(), ru.getToPlace());
				finalCost += finaRateForTrip*d.getValue();
				System.out.println(finaRateForTrip);
				System.out.println(finaRateForTrip*d.getValue());
			}
		}
		return finalCost;
	}

}
