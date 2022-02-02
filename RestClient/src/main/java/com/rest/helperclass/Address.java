package com.rest.helperclass;

public class Address {

	private Integer AddressId;
	private String streetname;
	private String state;

	public Integer getAddressId() {
		return AddressId;
	}

	public void setAddressId(Integer addressId) {
		AddressId = addressId;
	}

	public String getStreetname() {
		return streetname;
	}

	public void setStreetname(String streetname) {
		this.streetname = streetname;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "Address [AddressId=" + AddressId + ", streetname=" + streetname + ", state=" + state + "]";
	}

}
