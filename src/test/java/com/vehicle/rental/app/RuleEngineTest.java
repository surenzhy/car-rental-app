package com.vehicle.rental.app;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.vehicle.rental.app.business.rules.AdditionalPassengerRateCalculationRule;
import com.vehicle.rental.app.business.rules.DisountRateCalculationRule;
import com.vehicle.rental.app.business.rules.FuelTypeRateCalculationRule;
import com.vehicle.rental.app.business.rules.RateCalculationRule;
import com.vehicle.rental.app.business.rules.RuleEngine;
import com.vehicle.rental.app.business.rules.VehicleTypeRateCalculationRule;
import com.vehicle.rental.app.config.IEnv;
import com.vehicle.rental.app.config.RentalAppEvironment;
import com.vehicle.rental.app.domain.Route;
import com.vehicle.rental.app.domain.Trip;
import com.vehicle.rental.app.domain.Vehicle;
import com.vehicle.rental.app.domain.VehicleType;


@RunWith(MockitoJUnitRunner.class)
public class RuleEngineTest {
	
	@InjectMocks
	private RuleEngine re;
	
	@Before
	public void init() {
		List<RateCalculationRule> rules = new ArrayList<>();
		rules.add(new AdditionalPassengerRateCalculationRule());
		rules.add(new DisountRateCalculationRule());
		rules.add(new FuelTypeRateCalculationRule());
		rules.add(new VehicleTypeRateCalculationRule());
		re.setRateCalRules(rules);
	}

	@MockBean
	IEnv rEnv;
	
	private String CAR = "CAR";
	private String BUS = "BUS";
	private String PETROL = "PETROL";
	private String DIESEL = "DIESEL";


	@Test
	public void when_no_bus_and_noac_and_diesel_and_under_seating_capacity_than_valid_rate() {
		Assert.assertEquals(15.0-1,  re.getFinalRatePerKMForTrip(createTripData("swift", "dezire", CAR, DIESEL, false, "Pune", "Mumbai", env.getSEATING_CAPACITY_MAP().get(CAR), env.getSEATING_CAPACITY_MAP().get(CAR))), 0.0);
	}
	
	@Test
	public void when_no_bus_and_ac_and_diesel_and_under_seating_capacity_than_valid_rate() {
		Assert.assertEquals(15.0-1+2,  re.getFinalRatePerKMForTrip(createTripData("swift", "dezire", CAR, DIESEL, true, "Pune", "Mumbai", env.getSEATING_CAPACITY_MAP().get(CAR), env.getSEATING_CAPACITY_MAP().get(CAR))), 0.0);
	}
	

	@Test
	public void when_no_bus_and_ac_and_diesel_and_over_one_seating_capacity_than_valid_rate() {
		Assert.assertEquals(15.0-1+2+1,  re.getFinalRatePerKMForTrip(createTripData("swift", "dezire", CAR, DIESEL, true, "Pune", "Mumbai", env.getSEATING_CAPACITY_MAP().get(CAR), env.getSEATING_CAPACITY_MAP().get(CAR)+1)), 0.0);
	}

	@Test
	public void when_no_bus_and_noac_and_diesel_and_over_one_seating_capacity_than_valid_rate() {
		Assert.assertEquals(15.0-1+1,  re.getFinalRatePerKMForTrip(createTripData("swift", "dezire", CAR, DIESEL, false, "Pune", "Mumbai", env.getSEATING_CAPACITY_MAP().get(CAR), env.getSEATING_CAPACITY_MAP().get(CAR)+1)), 0.0);
	}
	

	@Test
	public void when_no_bus_and_noac_and_diesel_and_over_more_than_one_seating_capacity_than_valid_rate() {
		int overCapacity = 2;
		Assert.assertEquals(15.0-1+overCapacity*1,  re.getFinalRatePerKMForTrip(createTripData("swift", "dezire", CAR, DIESEL, false, "Pune", "Mumbai", env.getSEATING_CAPACITY_MAP().get(CAR), env.getSEATING_CAPACITY_MAP().get(CAR)+2)), 0.0);
	}
	

	@Test
	public void when_no_bus_and_noac_and_petrol_and_under_seating_capacity_than_valid_rate() {
		Assert.assertEquals(15.0,  re.getFinalRatePerKMForTrip(createTripData("swift", "dezire", CAR, PETROL, false, "Pune", "Mumbai", env.getSEATING_CAPACITY_MAP().get(CAR), env.getSEATING_CAPACITY_MAP().get(CAR))), 0.0);
	}
	
	@Test
	public void when_no_bus_and_ac_and_petrol_and_under_seating_capacity_than_valid_rate() {
		Assert.assertEquals(15.0+2,  re.getFinalRatePerKMForTrip(createTripData("swift", "dezire", CAR, PETROL, true, "Pune", "Mumbai", env.getSEATING_CAPACITY_MAP().get(CAR), env.getSEATING_CAPACITY_MAP().get(CAR))), 0.0);
	}
	

	@Test
	public void when_no_bus_and_ac_and_petrol_and_over_one_seating_capacity_than_valid_rate() {
		Assert.assertEquals(15.0+2+1,  re.getFinalRatePerKMForTrip(createTripData("swift", "dezire", CAR, PETROL, true, "Pune", "Mumbai", env.getSEATING_CAPACITY_MAP().get(CAR), env.getSEATING_CAPACITY_MAP().get(CAR)+1)), 0.0);
	}

	@Test
	public void when_no_bus_and_noac_and_petrol_and_over_one_seating_capacity_than_valid_rate() {
		Assert.assertEquals(15.0+1,  re.getFinalRatePerKMForTrip(createTripData("swift", "dezire", CAR, PETROL, false, "Pune", "Mumbai", env.getSEATING_CAPACITY_MAP().get(CAR),env.getSEATING_CAPACITY_MAP().get(CAR)+1)), 0.0);
	}
	

	@Test
	public void when_no_bus_and_noac_and_petrol_and_over_more_than_one_seating_capacity_than_valid_rate() {
		int overCapacity = 2;
		Assert.assertEquals(15.0+overCapacity*1,  re.getFinalRatePerKMForTrip(createTripData("swift", "dezire", CAR, PETROL, false, "Pune", "Mumbai", env.getSEATING_CAPACITY_MAP().get(CAR), env.getSEATING_CAPACITY_MAP().get(CAR)+2)), 0.0);
	}
	

	@Test
	public void when_bus_and_noac_and_petrol_and_under_seating_capacity_than_valid_rate() {
		Assert.assertEquals(15.0-15*.02,  re.getFinalRatePerKMForTrip(createTripData("swift", "dezire", BUS, PETROL, false, "Pune", "Mumbai", env.getSEATING_CAPACITY_MAP().get(BUS), env.getSEATING_CAPACITY_MAP().get(BUS))), 0.0);
	}
	
	@Test
	public void when_bus_and_ac_and_petrol_and_under_seating_capacity_than_valid_rate() {
		Assert.assertEquals(15.0-15*.02+2,  re.getFinalRatePerKMForTrip(createTripData("swift", "dezire", BUS, PETROL, true, "Pune", "Mumbai", env.getSEATING_CAPACITY_MAP().get(BUS), env.getSEATING_CAPACITY_MAP().get(BUS))), 0.0);
	}
	

	@Test
	public void when_bus_and_ac_and_petrol_and_over_one_seating_capacity_than_valid_rate() {
		Assert.assertEquals(15.0-15*.02+2+1,  re.getFinalRatePerKMForTrip(createTripData("swift", "dezire", BUS, PETROL, true, "Pune", "Mumbai", env.getSEATING_CAPACITY_MAP().get(BUS), env.getSEATING_CAPACITY_MAP().get(BUS)+1)), 0.0);
	}

	@Test
	public void when_bus_and_noac_and_petrol_and_over_one_seating_capacity_than_valid_rate() {
		Assert.assertEquals(15.0-15*.02+1,  re.getFinalRatePerKMForTrip(createTripData("swift", "dezire", BUS, PETROL, false, "Pune", "Mumbai", env.getSEATING_CAPACITY_MAP().get(BUS), env.getSEATING_CAPACITY_MAP().get(BUS)+1)), 0.0);
	}
	

	@Test
	public void when_bus_and_noac_and_petrol_and_over_more_than_one_seating_capacity_than_valid_rate() {
		int overCapacity = 2;
		Assert.assertEquals(15.0-15*.02+overCapacity*1,  re.getFinalRatePerKMForTrip(createTripData("swift", "dezire", BUS, PETROL, false, "Pune", "Mumbai", env.getSEATING_CAPACITY_MAP().get(BUS), env.getSEATING_CAPACITY_MAP().get(BUS)+2)), 0.0);
	}

	
	@Test
	public void when_bus_and_noac_and_diesel_and_under_seating_capacity_than_valid_rate() {
		Assert.assertEquals((15.0-1)-(15-1)*.02,  re.getFinalRatePerKMForTrip(createTripData("swift", "dezire", BUS, DIESEL, false, "Pune", "Mumbai", env.getSEATING_CAPACITY_MAP().get(BUS), env.getSEATING_CAPACITY_MAP().get(BUS))), 0.0);
	}
	
	@Test
	public void when_bus_and_ac_and_diesel_and_under_seating_capacity_than_valid_rate() {
		Assert.assertEquals((15.0-1)-(15-1)*.02+2,  re.getFinalRatePerKMForTrip(createTripData("swift", "dezire", BUS, DIESEL, true, "Pune", "Mumbai", env.getSEATING_CAPACITY_MAP().get(BUS), env.getSEATING_CAPACITY_MAP().get(BUS))), 0.0);
	}
	

	@Test
	public void when_bus_and_ac_and_diesel_and_over_one_seating_capacity_than_valid_rate() {
		Assert.assertEquals((15.0-1)-(15-1)*.02+2+1,  re.getFinalRatePerKMForTrip(createTripData("swift", "dezire", BUS, DIESEL, true, "Pune", "Mumbai", env.getSEATING_CAPACITY_MAP().get(BUS), env.getSEATING_CAPACITY_MAP().get(BUS)+1)), 0.0);
	}

	@Test
	public void when_bus_and_noac_and_diesel_and_over_one_seating_capacity_than_valid_rate() {
		Assert.assertEquals((15.0-1)-(15-1)*.02+1,  re.getFinalRatePerKMForTrip(createTripData("swift", "dezire", BUS, DIESEL, false, "Pune", "Mumbai", env.getSEATING_CAPACITY_MAP().get(BUS), env.getSEATING_CAPACITY_MAP().get(BUS)+1)), 0.0);
	}
	

	@Test
	public void when_bus_and_noac_and_diesel_and_over_more_than_one_seating_capacity_than_valid_rate() {
		int overCapacity = 2;
		Assert.assertEquals((15.0-1)-(15-1)*.02+overCapacity*1,  re.getFinalRatePerKMForTrip(createTripData("swift", "dezire", BUS, DIESEL, false, "Pune", "Mumbai", env.getSEATING_CAPACITY_MAP().get(BUS), env.getSEATING_CAPACITY_MAP().get(BUS)+2)), 0.0);
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
	
	
	IEnv env = new RentalAppEvironment();

}
