package com.vehicle.rental.app.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class RentalAppEvironment implements IEnv {

	public final Map<String, Integer> SEATING_CAPACITY_MAP = new HashMap<>();

	public RentalAppEvironment(){
		initEnv();
	}
	
	private void initEnv() {
		SEATING_CAPACITY_MAP.put("CAR", 5);
		SEATING_CAPACITY_MAP.put("BUS", 25);
		SEATING_CAPACITY_MAP.put("SUV", 7);
		SEATING_CAPACITY_MAP.put("VAN", 15);
	}

	/* (non-Javadoc)
	 * @see com.vehicle.rental.app.config.IEnv#getSEATING_CAPACITY_MAP()
	 */
	@Override
	public Map<String, Integer> getSEATING_CAPACITY_MAP() {
		return SEATING_CAPACITY_MAP;
	}
	
}
