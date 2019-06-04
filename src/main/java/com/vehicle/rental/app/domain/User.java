package com.vehicle.rental.app.domain;

public class User {

	private int id;

	private String username;

	private String email;
	
	public User(String username, String email) {
		super();
		this.username = username;
		this.email = email;
	}

	public User(String email) {
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
