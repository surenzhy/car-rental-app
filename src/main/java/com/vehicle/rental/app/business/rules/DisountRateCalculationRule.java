package com.vehicle.rental.app.business.rules;

import org.springframework.stereotype.Component;

import com.vehicle.rental.app.domain.Trip;

@Component
public class DisountRateCalculationRule extends RateCalculationRule {

	private final Integer ORDER = 2;
	private final Double DEFAULT_STANDARD_RATE = Double.valueOf(15);

	public Double applyBusinessRuleAndGetRateForVehicle(Trip trip, Double inputRate) {
		Double outputRate = null;
		if (null == inputRate) {
			inputRate = DEFAULT_STANDARD_RATE;
		} else {
			outputRate = inputRate;
		}
		if (null != trip && null != trip.getVehicle() && null != trip.getVehicle().getVehicleType()
				&& null != trip.getVehicle().getVehicleType().getType()) {
			if ("BUS".equals(trip.getVehicle().getVehicleType().getType())) {
				outputRate -= outputRate * 0.02;
			}
		}
		return outputRate;
	}

	public Integer getORDER() {
		return ORDER;
	}
}
