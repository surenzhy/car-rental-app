package com.vehicle.rental.app.domain;

public class Customer {

	private int id;

	private String username;

	private String email;
	
	public Customer(String username, String email) {
		super();
		this.username = username;
		this.email = email;
	}

	public Customer(String email) {
		super();
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getEmail() {
		return email;
	}	
	
}
