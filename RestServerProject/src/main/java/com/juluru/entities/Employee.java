package com.juluru.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer empid;

	private String name;
	//private Integer addressaddid;
	private Integer age;
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
//	@OneToOne
//	@JoinColumn(name = "addressaddid", referencedColumnName = "AddressId", insertable = false, updatable = false)
//	private Address address;
//	public Integer getEmpid() {
//		return empid;
//	}
	
	public void setEmpid(Integer empid) {
		this.empid = empid;
	}
	@Override
	public String toString() {
		return "Employee [empid=" + empid + ", name=" + name + ", age=" + age + "]";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
/*	public Integer getAddressaddid() {
		return addressaddid;
	}
	public void setAddressaddid(Integer addressaddid) {
		this.addressaddid = addressaddid;
	}*/
//	public Address getAddress() {
//		return address;
//	}
//	public void setAddress(Address address) {
//		this.address = address;
//	}
}
