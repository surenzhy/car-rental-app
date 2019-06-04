package com.vehicle.rental.app.util;

import org.springframework.stereotype.Component;

import com.vehicle.rental.app.bean.Distance;
import com.vehicle.rental.app.bean.DistanceUnit;

@Component
public class RouteInformationHelper{

	public Distance getDistanceBetweenRoutes(String from, String to) {
		Distance d = new Distance(DistanceUnit.KM, 0.0);
		if("Pune".equalsIgnoreCase(from) && "Mumbai".equalsIgnoreCase(to)) {
			d = new Distance(DistanceUnit.KM, Double.valueOf(200));
		}else if("Pune".equalsIgnoreCase(from) && "Banglore".equalsIgnoreCase(to)) {
			d = new Distance(DistanceUnit.KM, Double.valueOf(1000));
		}else if("Mumbai".equalsIgnoreCase(from) && "Delhi".equalsIgnoreCase(to)) {
			d = new Distance(DistanceUnit.KM, Double.valueOf(2050));
		}else if("Mumbai".equalsIgnoreCase(from) && "Chennai".equalsIgnoreCase(to)) {
			d = new Distance(DistanceUnit.KM, Double.valueOf(1234.5));
		}
		return d;
	}

	public boolean isRouteAvailable(String from, String to) {
		// TODO Auto-generated method stub
		return false;
	}
}
