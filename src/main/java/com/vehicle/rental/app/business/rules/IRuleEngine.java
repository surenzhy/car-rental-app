package com.vehicle.rental.app.business.rules;

import com.vehicle.rental.app.domain.Trip;

public interface IRuleEngine {

	Double getFinalRatePerKMForTrip(Trip trip);

}