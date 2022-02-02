package com.juluru;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestServerProjectApplication  {

	//@Autowired
	//EntityManagerFactory factory;
	/*
	 * @Autowired Employee employee;
	 * 
	 * @Autowired Address address;
	 */

	public static void main(String[] args) {
		SpringApplication.run(RestServerProjectApplication.class, args);
	}

/*	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub

		EntityManager createEntityManager = factory.createEntityManager();
		Address address = new Address();
		Employee employee = new Employee();
		createEntityManager.getTransaction().begin();
		address.setState("andhra");
		address.setStreetname("nuzvidu road");
		// createEntityManager.
		createEntityManager.persist(address);
		System.out.println("after saving entity" + address);
		employee.setAddress(address);
		employee.setName("raja");
		employee.setAddressaddid(address.getAddressId());
		createEntityManager.persist(employee);
		System.out.println(employee);
		createEntityManager.getTransaction().commit();
		Query createNativeQuery = createEntityManager.createQuery("select a from Employee a");
		List resultList = createNativeQuery.getResultList();
		System.out.println(resultList + "resultList");
	}*/

}
