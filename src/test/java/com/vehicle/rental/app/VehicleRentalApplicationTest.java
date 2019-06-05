package com.vehicle.rental.app;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.vehicle.rental.app.business.rules.RuleEngine;
import com.vehicle.rental.app.config.IEnv;
import com.vehicle.rental.app.config.RentalAppEvironment;
import com.vehicle.rental.app.domain.Route;
import com.vehicle.rental.app.domain.Trip;
import com.vehicle.rental.app.domain.Vehicle;
import com.vehicle.rental.app.domain.VehicleType;
import com.vehicle.rental.app.exception.UnSupportedFuelTypeException;
import com.vehicle.rental.app.exception.UnSupportedRouteException;
import com.vehicle.rental.app.exception.UnSupportedVehicleTypeException;
import com.vehicle.rental.app.service.impl.TripReservationServiceImpl;

/**
 * Unit test for simple App.
 */
@SpringBootTest
public class VehicleRentalApplicationTest {

	private String CAR = "CAR";
	private String BUS = "BUS";
	private String PETROL = "PETROL";
	private String DIESEL = "DIESEL";

	
	@InjectMocks
	TripReservationServiceImpl service;
	IEnv env = new RentalAppEvironment();

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		service.setEnv(env);
	}
	
	@Mock
	RuleEngine ruleEngine;
	
	
	
	@Test
	public void when_pune_to_mumbai_no_bus_diesel_noac_under_seating_capacity_than_valid_trip_cost() {
		String from = "Pune";
		String to = "Mumbai";
		Trip tData = createTripData("swift", "dezire", CAR, DIESEL, false, from, to, env.getSEATING_CAPACITY_MAP().get(CAR), env.getSEATING_CAPACITY_MAP().get(CAR));
		Double finalRate = 15.0-1;
		when(ruleEngine.getFinalRatePerKMForTrip(tData)).thenReturn(finalRate);
		Assert.assertEquals((finalRate * env.getDistanceBetweenRoutes(from, to).getValue()), service.calculateTripExpense(tData), 0.0);
		
	}

	@Test
	public void when_pune_to_mumbai_no_bus_diesel_ac_under_seating_capacity_than_valid_trip_cost() {
		String from = "Pune";
		String to = "Mumbai";
		Trip tData = createTripData("swift", "dezire", CAR, DIESEL, false, from, to, env.getSEATING_CAPACITY_MAP().get(CAR), env.getSEATING_CAPACITY_MAP().get(CAR));
		Double finalRate = 15.0-1+2;
		when(ruleEngine.getFinalRatePerKMForTrip(tData)).thenReturn(finalRate);
		Assert.assertEquals((finalRate * env.getDistanceBetweenRoutes(from, to).getValue()), service.calculateTripExpense(tData), 0.0);
		
	}
	

	@Test
	public void when_pune_to_mumbai_bus_diesel_noac_under_seating_capacity_than_valid_trip_cost() {
		String from = "Pune";
		String to = "Mumbai";
		Trip tData = createTripData("swift", "dezire", CAR, DIESEL, false, from, to, env.getSEATING_CAPACITY_MAP().get(CAR), env.getSEATING_CAPACITY_MAP().get(CAR));
		Double finalRate = (15.0-1)-(15.0-1)*0.02;
		when(ruleEngine.getFinalRatePerKMForTrip(tData)).thenReturn(finalRate);
		Assert.assertEquals((finalRate * env.getDistanceBetweenRoutes(from, to).getValue()), service.calculateTripExpense(tData), 0.0);
		
	}

	@Test
	public void when_pune_to_mumbai_bus_diesel_ac_under_seating_capacity_than_valid_trip_cost() {
		String from = "Pune";
		String to = "Mumbai";
		Trip tData = createTripData("swift", "dezire", CAR, DIESEL, false, from, to, env.getSEATING_CAPACITY_MAP().get(CAR), env.getSEATING_CAPACITY_MAP().get(CAR));
		Double finalRate = (15.0-1)-(15.0-1)*0.02+2;
		when(ruleEngine.getFinalRatePerKMForTrip(tData)).thenReturn(finalRate);
		Assert.assertEquals((finalRate * env.getDistanceBetweenRoutes(from, to).getValue()), service.calculateTripExpense(tData), 0.0);
		
	}
	


	@Test
	public void when_pune_to_mumbai_no_bus_petrol_noac_under_seating_capacity_than_valid_trip_cost() {
		String from = "Pune";
		String to = "Mumbai";
		Trip tData = createTripData("swift", "dezire", CAR, DIESEL, false, from, to, env.getSEATING_CAPACITY_MAP().get(CAR), env.getSEATING_CAPACITY_MAP().get(CAR));
		Double finalRate = 15.0;
		when(ruleEngine.getFinalRatePerKMForTrip(tData)).thenReturn(finalRate);
		Assert.assertEquals((finalRate * env.getDistanceBetweenRoutes(from, to).getValue()), service.calculateTripExpense(tData), 0.0);
		
	}

	@Test
	public void when_pune_to_mumbai_no_bus_petrol_ac_under_seating_capacity_than_valid_trip_cost() {
		String from = "Pune";
		String to = "Mumbai";
		Trip tData = createTripData("swift", "dezire", CAR, DIESEL, false, from, to, env.getSEATING_CAPACITY_MAP().get(CAR), env.getSEATING_CAPACITY_MAP().get(CAR));
		Double finalRate = 15.0+2;
		when(ruleEngine.getFinalRatePerKMForTrip(tData)).thenReturn(finalRate);
		Assert.assertEquals((finalRate * env.getDistanceBetweenRoutes(from, to).getValue()), service.calculateTripExpense(tData), 0.0);
		
	}
	

	@Test
	public void when_pune_to_mumbai_bus_petrol_noac_under_seating_capacity_than_valid_trip_cost() {
		String from = "Pune";
		String to = "Mumbai";
		Trip tData = createTripData("swift", "dezire", CAR, DIESEL, false, from, to, env.getSEATING_CAPACITY_MAP().get(CAR), env.getSEATING_CAPACITY_MAP().get(CAR));
		Double finalRate = 15.0*0.02;
		when(ruleEngine.getFinalRatePerKMForTrip(tData)).thenReturn(finalRate);
		Assert.assertEquals((finalRate * env.getDistanceBetweenRoutes(from, to).getValue()), service.calculateTripExpense(tData), 0.0);
		
	}

	@Test
	public void when_pune_to_mumbai_bus_petrol_ac_under_seating_capacity_than_valid_trip_cost() {
		String from = "Pune";
		String to = "Mumbai";
		Trip tData = createTripData("swift", "dezire", CAR, DIESEL, false, from, to, env.getSEATING_CAPACITY_MAP().get(CAR), env.getSEATING_CAPACITY_MAP().get(CAR));
		Double finalRate = 15.0-15.0*0.02+2;
		when(ruleEngine.getFinalRatePerKMForTrip(tData)).thenReturn(finalRate);
		Assert.assertEquals((finalRate * env.getDistanceBetweenRoutes(from, to).getValue()), service.calculateTripExpense(tData), 0.0);
		
	}
	

////////////////////
	
	
	@Test
	public void when_pune_to_banglore_no_bus_diesel_noac_under_seating_capacity_than_valid_trip_cost() {
		String from = "Pune";
		String to = "Banglore";
		Trip tData = createTripData("swift", "dezire", CAR, DIESEL, false, from, to, env.getSEATING_CAPACITY_MAP().get(CAR), env.getSEATING_CAPACITY_MAP().get(CAR));
		Double finalRate = 15.0-1;
		when(ruleEngine.getFinalRatePerKMForTrip(tData)).thenReturn(finalRate);
		Assert.assertEquals((finalRate * env.getDistanceBetweenRoutes(from, to).getValue()), service.calculateTripExpense(tData), 0.0);
		
	}

	@Test
	public void when_pune_to_banglore_no_bus_diesel_ac_under_seating_capacity_than_valid_trip_cost() {
		String from = "Pune";
		String to = "Banglore";
		Trip tData = createTripData("swift", "dezire", CAR, DIESEL, false, from, to, env.getSEATING_CAPACITY_MAP().get(CAR), env.getSEATING_CAPACITY_MAP().get(CAR));
		Double finalRate = 15.0-1+2;
		when(ruleEngine.getFinalRatePerKMForTrip(tData)).thenReturn(finalRate);
		Assert.assertEquals((finalRate * env.getDistanceBetweenRoutes(from, to).getValue()), service.calculateTripExpense(tData), 0.0);
		
	}
	

	@Test
	public void when_pune_to_banglore_bus_diesel_noac_under_seating_capacity_than_valid_trip_cost() {
		String from = "Pune";
		String to = "Banglore";
		Trip tData = createTripData("swift", "dezire", CAR, DIESEL, false, from, to, env.getSEATING_CAPACITY_MAP().get(CAR), env.getSEATING_CAPACITY_MAP().get(CAR));
		Double finalRate = (15.0-1)-(15.0-1)*0.02;
		when(ruleEngine.getFinalRatePerKMForTrip(tData)).thenReturn(finalRate);
		Assert.assertEquals((finalRate * env.getDistanceBetweenRoutes(from, to).getValue()), service.calculateTripExpense(tData), 0.0);
		
	}

	@Test
	public void when_pune_to_banglore_bus_diesel_ac_under_seating_capacity_than_valid_trip_cost() {
		String from = "Pune";
		String to = "Banglore";
		Trip tData = createTripData("swift", "dezire", CAR, DIESEL, false, from, to, env.getSEATING_CAPACITY_MAP().get(CAR), env.getSEATING_CAPACITY_MAP().get(CAR));
		Double finalRate = (15.0-1)-(15.0-1)*0.02+2;
		when(ruleEngine.getFinalRatePerKMForTrip(tData)).thenReturn(finalRate);
		Assert.assertEquals((finalRate * env.getDistanceBetweenRoutes(from, to).getValue()), service.calculateTripExpense(tData), 0.0);
		
	}
	


	@Test
	public void when_pune_to_banglore_no_bus_petrol_noac_under_seating_capacity_than_valid_trip_cost() {
		String from = "Pune";
		String to = "Banglore";
		Trip tData = createTripData("swift", "dezire", CAR, DIESEL, false, from, to, env.getSEATING_CAPACITY_MAP().get(CAR), env.getSEATING_CAPACITY_MAP().get(CAR));
		Double finalRate = 15.0;
		when(ruleEngine.getFinalRatePerKMForTrip(tData)).thenReturn(finalRate);
		Assert.assertEquals((finalRate * env.getDistanceBetweenRoutes(from, to).getValue()), service.calculateTripExpense(tData), 0.0);
		
	}

	@Test
	public void when_pune_to_banglore_no_bus_petrol_ac_under_seating_capacity_than_valid_trip_cost() {
		String from = "Pune";
		String to = "Banglore";
		Trip tData = createTripData("swift", "dezire", CAR, DIESEL, false, from, to, env.getSEATING_CAPACITY_MAP().get(CAR), env.getSEATING_CAPACITY_MAP().get(CAR));
		Double finalRate = 15.0+2;
		when(ruleEngine.getFinalRatePerKMForTrip(tData)).thenReturn(finalRate);
		Assert.assertEquals((finalRate * env.getDistanceBetweenRoutes(from, to).getValue()), service.calculateTripExpense(tData), 0.0);
		
	}
	

	@Test
	public void when_pune_to_banglore_bus_petrol_noac_under_seating_capacity_than_valid_trip_cost() {
		String from = "Pune";
		String to = "Banglore";
		Trip tData = createTripData("swift", "dezire", CAR, DIESEL, false, from, to, env.getSEATING_CAPACITY_MAP().get(CAR), env.getSEATING_CAPACITY_MAP().get(CAR));
		Double finalRate = 15.0*0.02;
		when(ruleEngine.getFinalRatePerKMForTrip(tData)).thenReturn(finalRate);
		Assert.assertEquals((finalRate * env.getDistanceBetweenRoutes(from, to).getValue()), service.calculateTripExpense(tData), 0.0);
		
	}

	@Test
	public void when_pune_to_banglore_bus_petrol_ac_under_seating_capacity_than_valid_trip_cost() {
		String from = "Pune";
		String to = "Banglore";
		Trip tData = createTripData("swift", "dezire", CAR, DIESEL, false, from, to, env.getSEATING_CAPACITY_MAP().get(CAR), env.getSEATING_CAPACITY_MAP().get(CAR));
		Double finalRate = 15.0-15.0*0.02+2;
		when(ruleEngine.getFinalRatePerKMForTrip(tData)).thenReturn(finalRate);
		Assert.assertEquals((finalRate * env.getDistanceBetweenRoutes(from, to).getValue()), service.calculateTripExpense(tData), 0.0);
		
	}
	

	
	private Trip createTripData(String company, String model, String type, String fuelType, boolean isAC, 
			 String from, String to, Integer vehicleCapcaity, Integer totalPassengers) {
	
		//VehicleType vt = new VehicleType("swift", "dezire", BUS, PETROL, false);
		VehicleType vt = new VehicleType(company, model, type, fuelType, isAC);
		Vehicle vehicle = new Vehicle(vt, vehicleCapcaity);
		Route r = new Route(from, to);
		List<Route> rL = new ArrayList<>();
		rL.add(r);
		Trip trip = new Trip(vehicle, rL, null, totalPassengers);
		return trip;
	}

	@Test(expected = UnSupportedVehicleTypeException.class)
	public void when_invalid_vehicle_type_than_exception() {
		String from = "Pune";
		String to = "Banglore";
		Trip tData = createTripData("swift", "dezire", "DUMMY", DIESEL, false, from, to, env.getSEATING_CAPACITY_MAP().get(CAR), env.getSEATING_CAPACITY_MAP().get(CAR));
		Double finalRate = 15.0-15.0*0.02+2;
		when(ruleEngine.getFinalRatePerKMForTrip(tData)).thenReturn(finalRate);
		service.calculateTripExpense(tData);
	}

	@Test(expected = UnSupportedFuelTypeException.class)
	public void when_invalid_fuel_type_than_exception() {
		String from = "Pune";
		String to = "Banglore";
		Trip tData = createTripData("swift", "dezire", "BUS", "INVALID", false, from, to, env.getSEATING_CAPACITY_MAP().get(CAR), env.getSEATING_CAPACITY_MAP().get(CAR));
		Double finalRate = 15.0-15.0*0.02+2;
		when(ruleEngine.getFinalRatePerKMForTrip(tData)).thenReturn(finalRate);
		service.calculateTripExpense(tData);
	}

	@Test(expected = UnSupportedRouteException.class)
	public void when_invalid_route_type_than_exception() {
		String from = "Invalid";
		String to = "Banglore";
		Trip tData = createTripData("swift", "dezire", "BUS", "PETROL", false, from, to, env.getSEATING_CAPACITY_MAP().get(CAR), env.getSEATING_CAPACITY_MAP().get(CAR));
		Double finalRate = 15.0-15.0*0.02+2;
		when(ruleEngine.getFinalRatePerKMForTrip(tData)).thenReturn(finalRate);
		service.calculateTripExpense(tData);
	}

	
}
