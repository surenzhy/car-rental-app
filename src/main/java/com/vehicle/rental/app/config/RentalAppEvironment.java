package com.vehicle.rental.app.config;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.vehicle.rental.app.bean.Distance;
import com.vehicle.rental.app.bean.DistanceUnit;

@Component
public class RentalAppEvironment implements IEnv {

	public final Map<String, Integer> SEATING_CAPACITY_MAP = new HashMap<>();

	public final Set<String> ROUTES = new HashSet<>();
	
	
	public RentalAppEvironment(){
		initEnv();
	}
	
	private void initEnv() {
		SEATING_CAPACITY_MAP.put("CAR", 5);
		SEATING_CAPACITY_MAP.put("BUS", 25);
		SEATING_CAPACITY_MAP.put("SUV", 7);
		SEATING_CAPACITY_MAP.put("VAN", 15);
		ROUTES.add("PuneMumbai");
		ROUTES.add("PuneBanglore");
		ROUTES.add("MumbaiDelhi");
		ROUTES.add("MumbaiChennai");
	}

	
	
	public Set<String> getROUTES() {
		return ROUTES;
	}

	/* (non-Javadoc)
	 * @see com.vehicle.rental.app.config.IEnv#getSEATING_CAPACITY_MAP()
	 */
	@Override
	public Map<String, Integer> getSEATING_CAPACITY_MAP() {
		return SEATING_CAPACITY_MAP;
	}
	
	public Distance getDistanceBetweenRoutes(String from, String to) {
		Distance d = new Distance(DistanceUnit.KM, 0.0);
		if(ROUTES.contains(from+to)) {
			if("Pune".equalsIgnoreCase(from) && "Mumbai".equalsIgnoreCase(to)) {
				d = new Distance(DistanceUnit.KM, Double.valueOf(200));
			}else if("Pune".equalsIgnoreCase(from) && "Banglore".equalsIgnoreCase(to)) {
				d = new Distance(DistanceUnit.KM, Double.valueOf(1000));
			}else if("Mumbai".equalsIgnoreCase(from) && "Delhi".equalsIgnoreCase(to)) {
				d = new Distance(DistanceUnit.KM, Double.valueOf(2050));
			}else if("Mumbai".equalsIgnoreCase(from) && "Chennai".equalsIgnoreCase(to)) {
				d = new Distance(DistanceUnit.KM, Double.valueOf(1234.5));
			}
		}
		return d;
	}

	
}
