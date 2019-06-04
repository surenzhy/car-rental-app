package com.vehicle.rental.app.business.rules;

import org.springframework.stereotype.Component;

import com.vehicle.rental.app.domain.Trip;

@Component
public class FuelTypeRateCalculationRule extends RateCalculationRule{

	private final Integer ORDER = 1;
    private final Double DEFAULT_STANDARD_RATE = Double.valueOf(15);
	
	public Double applyBusinessRuleAndGetRateForVehicle(Trip trip, Double inputRate) {
		Double outputRate = null;
		if(null != trip && null != trip.getVehicle() && null != trip.getVehicle().getVehicleType() && null != trip.getVehicle().getVehicleType().getFuelType()) {
			if("PETROL".equals(trip.getVehicle().getVehicleType().getFuelType())) {
				outputRate = DEFAULT_STANDARD_RATE;
			}else if("DIESEL".equals(trip.getVehicle().getVehicleType().getFuelType())) {
				outputRate = DEFAULT_STANDARD_RATE - 1;
			}else {
				outputRate = DEFAULT_STANDARD_RATE;
			}
		}
		return outputRate;
	}

	public Integer getORDER() {
		return ORDER;
	}

}
