package com.vehicle.rental.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vehicle.rental.app.bean.Distance;
import com.vehicle.rental.app.business.rules.IRuleEngine;
import com.vehicle.rental.app.config.IEnv;
import com.vehicle.rental.app.domain.Route;
import com.vehicle.rental.app.domain.Trip;
import com.vehicle.rental.app.exception.UnSupportedFuelTypeException;
import com.vehicle.rental.app.exception.UnSupportedRouteException;
import com.vehicle.rental.app.exception.UnSupportedVehicleTypeException;
import com.vehicle.rental.app.service.ITripReservationService;

@Service
public class TripReservationServiceImpl implements ITripReservationService {

	@Autowired
	IRuleEngine ruleEngine;

	@Autowired
	IEnv env;

	public void setRuleEngine(IRuleEngine ruleEngine) {
		this.ruleEngine = ruleEngine;
	}

	public void setEnv(IEnv env) {
		this.env = env;
	}

	public IEnv getEnv() {
		return this.env;
	}

	@Override
	public Double calculateTripExpense(Trip trip) {
		Double finalCost = 0.0;
		if (validate(trip)) {
			Double finaRateForTrip = ruleEngine.getFinalRatePerKMForTrip(trip);
			if (null != trip && null != trip.getRoute()) {
				for (Route ru : trip.getRoute()) {
					Distance d = env.getDistanceBetweenRoutes(ru.getFromPlace(), ru.getToPlace());
					finalCost += finaRateForTrip * d.getValue();
				}
			}
		}
		return finalCost;
	}

	private boolean validate(Trip trip) {
		if (null != trip && null != trip.getVehicle() && null != trip.getVehicle().getVehicleType()) {
			if (null == env.getSEATING_CAPACITY_MAP().get(trip.getVehicle().getVehicleType().getType())) {
				throw new UnSupportedVehicleTypeException(
						"Vehicle type " + trip.getVehicle().getVehicleType().getType() + " is not supported");
			}
		}

		if (null != trip && null != trip.getVehicle() && null != trip.getVehicle().getVehicleType()) {
			if (!"PETROL".equals(trip.getVehicle().getVehicleType().getFuelType())
					&& !"DIESEL".equals(trip.getVehicle().getVehicleType().getFuelType())) {
				throw new UnSupportedFuelTypeException(
						"Fuel type " + trip.getVehicle().getVehicleType().getFuelType() + " is not supported");
			}
		}

		if (null != trip.getRoute()) {
			for (Route rt : trip.getRoute()) {
				if (null != env.getROUTES() && !env.getROUTES().contains(rt.getFromPlace() + rt.getToPlace())) {
					throw new UnSupportedRouteException(
							"Route from " + rt.getFromPlace() +  ", route to " + rt.getToPlace() + " is not supported");
				}
			}
		}
		return true;
	}

}
