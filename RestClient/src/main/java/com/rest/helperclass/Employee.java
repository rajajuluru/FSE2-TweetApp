package com.rest.helperclass;


public class Employee {

	
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
	
	//private Address address;
	public Integer getEmpid() {
		return empid;
	}
	@Override
	public String toString() {
		return "Employee [empid=" + empid + ", name=" + name 
				+ "]";
	}
	public void setEmpid(Integer empid) {
		this.empid = empid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}


}
