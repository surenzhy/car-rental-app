package com.vehicle.rental.app.bean;

public final class Distance {

	private DistanceUnit unit;
	private Double value;

	public Distance(DistanceUnit unit, Double value) {
		this.unit = unit;
		this.value = value;
	}

	public DistanceUnit getUnit() {
		return unit;
	}

	public Double getValue() {
		return value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((unit == null) ? 0 : unit.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Distance other = (Distance) obj;
		if (unit != other.unit)
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

}
