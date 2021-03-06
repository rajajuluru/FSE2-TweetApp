package com.juluru.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.juluru.entities.Address;
import com.juluru.entities.Employee;
import com.juluru.repositories.AddressRepositories;
import com.juluru.repositories.EmployeeRepositories;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepositories employeeRepositories;
	@Autowired
	private AddressRepositories addressRepositories;

	@Transactional(readOnly=true,propagation=Propagation.REQUIRED)
	public List<Employee> findallEmp() {
		Sort BYAGE = Sort.by(Sort.Direction.ASC,"age");
		PageRequest.of(0, 5, BYAGE);
		//page starts with 0 (1 parameter)
		//size in each page(2nd parameter)
		//sorting
		//employeeRepositories.findById(id)
		return employeeRepositories.findAll(BYAGE);
	}
	

	public CompletableFuture<List<Employee>> findallEmpCompletable() {
		
	CompletableFuture<List<Employee>> supplyAsync = CompletableFuture.supplyAsync(()->{
		System.out.println(Thread.currentThread().getName()+"threaad name");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return employeeRepositories.findAll();
	});
		//return employeeRepositories.findAll();
	return supplyAsync;
	}
	
	@Transactional
	public Employee insertEmp(Employee emp) {
		// Address save2 = addressRepositories.save(emp.getAddress());
		// addressRepositories.
		 //emp.setAddressaddid(save2.getAddressId());
		 Employee save = employeeRepositories.save(emp);
		 return save;
}



}