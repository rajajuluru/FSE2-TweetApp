package com.juluru.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToOne;

@Entity
//@NamedEntityGraph(attributeNodes=@NamedAttributeNode(""),subclassSubgraphs=@)
public class Address {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
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
