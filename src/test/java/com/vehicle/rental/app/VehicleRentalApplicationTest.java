package com.vehicle.rental.app;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.vehicle.rental.app.business.rules.RuleEngine;
import com.vehicle.rental.app.domain.Route;
import com.vehicle.rental.app.domain.Trip;
import com.vehicle.rental.app.domain.Vehicle;
import com.vehicle.rental.app.domain.VehicleType;
import com.vehicle.rental.app.service.impl.TripReservationServiceImpl;

/**
 * Unit test for simple App.
 */
@SpringBootTest
public class VehicleRentalApplicationTest {

	@Autowired
	TripReservationServiceImpl service;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	
	
	@Test
	public void test() {
		System.out.println("test ");
	}

	

	@Test
	public void test_trip_cost_for_pertol_vehicle_for_pune_mumbai() {
		Trip tData = createTripData("swift", "dezire", "BUS", "PETROL", false, "Pune", "Mumbai");
		//System.out.println(service.calculateTripExpense(tData));
		
	}

	private Trip createTripData(String company, String model, String type, String fuelType, boolean isAC, 
			 String from, String to) {
	
		/*//VehicleType vt = new VehicleType("swift", "dezire", "BUS", "PETROL", false);
		VehicleType vt = new VehicleType(company, model, type, fuelType, isAC);
		//Vehicle vehicle = new Vehicle(vt);
		Route r = new Route(from, to);
		List<Route> rL = new ArrayList<>();
		rL.add(r);
		Trip trip = new Trip(vehicle, rL, null, 8);*/
		return null;
	}
	
}
