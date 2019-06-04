package com.vehicle.rental.app.business.rules;

import com.vehicle.rental.app.domain.Trip;

public abstract class RateCalculationRule {
	
	private Integer ORDER = 0;

	public abstract Double applyBusinessRuleAndGetRateForVehicle(Trip trip, Double inputRate);

	public Integer getORDER() {
		return ORDER;
	}
}
