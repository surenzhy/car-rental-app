package com.vehicle.rental.app.config;

import java.util.Map;
import java.util.Set;

import com.vehicle.rental.app.bean.Distance;

public interface IEnv {

	public Map<String, Integer> getSEATING_CAPACITY_MAP();

	public Distance getDistanceBetweenRoutes(String from, String to);

	public Set<String> getROUTES();

}