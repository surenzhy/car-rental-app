  package com.vehicle.rental.app.business.rules;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vehicle.rental.app.domain.Trip;

@Component
public class RuleEngine implements IRuleEngine {

	@Autowired
	List<RateCalculationRule> rateCalRules;
	
	public List<RateCalculationRule> getRateCalRules() {
		return rateCalRules;
	}

	
	
	public void setRateCalRules(List<RateCalculationRule> rateCalRules) {
		this.rateCalRules = rateCalRules;
	}



	/* (non-Javadoc)
	 * @see com.vehicle.rental.app.business.rules.IRuleEngine#getFinalRatePerKMForTrip(com.vehicle.rental.app.domain.Trip)
	 */
	@Override
	public Double getFinalRatePerKMForTrip(Trip trip) {
		Double finalRate = null;
		Collections.sort(rateCalRules, (r1, r2) -> {
			return r1.getORDER() - r2.getORDER();
		});
		for (RateCalculationRule rule : rateCalRules) {
			if (null != finalRate) {
				finalRate = rule.applyBusinessRuleAndGetRateForVehicle(trip, finalRate);
			} else {
				finalRate = rule.applyBusinessRuleAndGetRateForVehicle(trip, null);
			}

		}
		return finalRate;
	}

}
