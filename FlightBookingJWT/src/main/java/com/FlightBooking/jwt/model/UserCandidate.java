package com.FlightBooking.jwt.model;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class UserCandidate implements Serializable{


	public UserCandidate(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public UserCandidate() {
		super();
	}

	private String username;


	private String password;
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		UserCandidate other = (UserCandidate) obj;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Override
	public String toString() {
		return "UserCandidate [username=" + username + ", password=" + password + "]";
	}

	


}
