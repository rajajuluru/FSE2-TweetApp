package com.FlightBooking.helper;

import org.springframework.stereotype.Component;

@Component
public class UserDetailsHelperClass {
	
	public UserDetailsHelperClass(String username, String role) {
		super();
		this.username = username;
		this.role = role;
	}
	public UserDetailsHelperClass() {
		super();
	}
	private String username;
	private String role;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}

}
