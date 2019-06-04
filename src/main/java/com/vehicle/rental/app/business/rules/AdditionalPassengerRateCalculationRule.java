package com.vehicle.rental.app.business.rules;

import org.springframework.stereotype.Component;

import com.vehicle.rental.app.domain.Trip;

@Component
public class AdditionalPassengerRateCalculationRule extends RateCalculationRule {

	private final Integer ORDER = 4;
	private final Double DEFAULT_STANDARD_RATE = Double.valueOf(15);

	public Double applyBusinessRuleAndGetRateForVehicle(Trip trip, Double inputRate) {
		Double outputRate = null;
		if(null == inputRate) {
			inputRate = DEFAULT_STANDARD_RATE;
		}else {
			outputRate = inputRate;
		}
		if (null != trip && null != trip.getVehicle() && trip.getTotalPessangers() > trip.getVehicle().getSeatingCapacity()) {
			outputRate += trip.getTotalPessangers() - trip.getVehicle().getSeatingCapacity();
		}
		return outputRate;
	}

	public Integer getORDER() {
		return ORDER;
	}
}
