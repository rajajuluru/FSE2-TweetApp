package com.juluru.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.juluru.entities.Address;
import com.juluru.entities.Employee;
import com.juluru.repositories.AddressRepositories;
import com.juluru.repositories.EmployeeRepositories;
import com.juluru.service.EmployeeService;

@RestController

public class RequestController {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private AddressRepositories addressRepositories;

	@Autowired
	private EmployeeRepositories employeeRepositories;

	//
	@GetMapping(value = "findAllEmployee", produces={MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> findAllEmployee(HttpServletRequest request) throws InterruptedException {

		// int a=2/0;
		//Thread.sleep(2000);
		System.out.println(request.getHeaders("name")+"headers name");
		List<Employee> findallEmp = employeeService.findallEmp();
		ResponseEntity<List<Employee>> responseEntity = new ResponseEntity<List<Employee>>(findallEmp, HttpStatus.OK);

		return responseEntity;
	}
	
	@GetMapping(value = "findAllEmployeeCompletable")
	public  CompletableFuture<List<Employee>>  findAllEmployeeCompletable() throws InterruptedException, ExecutionException, TimeoutException {

		// int a=2/0;
		//Thread.sleep(2000);
		 CompletableFuture<List<Employee>> findallEmpCompletable = employeeService.findallEmpCompletable();
		//ResponseEntity<List<Employee>> responseEntity = new ResponseEntity<List<Employee>>(findallEmp, HttpStatus.OK);
		 try
		 {
			// List<Employee> list = findallEmpCompletable.get(6000, TimeUnit.MILLISECONDS);
			 return findallEmpCompletable;
		 } catch (Exception e) {
			// TODO: handle exception
			 e.printStackTrace();
			 List<Employee> arrayList = new ArrayList<Employee>();
				return CompletableFuture.completedFuture(arrayList);
		}
	
	
	}

	@PostMapping("insertEmployee")
	public ResponseEntity<?> insertEmployee(@RequestBody Employee emp) {
		System.out.println(emp);

		Employee insertEmp = employeeService.insertEmp(emp);
		ResponseEntity<Employee> responseEntity = new ResponseEntity<Employee>(insertEmp, HttpStatus.CREATED);
		return responseEntity;
	}

	@PutMapping("updateEmployee/{id}")
	public ResponseEntity<? extends Object> updateEmployee(@PathVariable(name = "id") Integer id,
			@RequestBody Employee emp) {

		System.out.println(emp);
		if (employeeRepositories.existsById(id)) {

			Optional<Employee> findById = employeeRepositories.findById(id);
			Employee employee = findById.get();
			employee.setAge(emp.getAge());
			employee.setName(emp.getName());
			employeeRepositories.save(employee);

			ResponseEntity<Employee> responseEntity = new ResponseEntity<Employee>(
					employeeRepositories.findById(id).get(), HttpStatus.OK);
			// responseEntity.getBody().get
			return responseEntity;
		} else {
			ResponseEntity<String> responseEntity = new ResponseEntity<String>("entity with id" + id + "not found",
					HttpStatus.NOT_FOUND);
			return responseEntity;
		}

	}

	@PutMapping("updateEmployeeAddress/{id}")
	public ResponseEntity<? extends Object> updateEmployeeAddress(@PathVariable(name = "id") Integer id,
			@RequestBody Address address) {

		System.out.println(address);
		if (employeeRepositories.existsById(id)) {

			Optional<Employee> findById = employeeRepositories.findById(id);
			Employee employee = findById.get();
			//Address address2 = employee.getAddress();
			Address address2 = new Address();
			address2.setState(address.getState());
			address2.setStreetname(address.getStreetname());

			Address save = addressRepositories.save(address2);

			ResponseEntity<Employee> responseEntity = new ResponseEntity<Employee>(
					employeeRepositories.findById(id).get(), HttpStatus.OK);
			// responseEntity.getBody().get
			return responseEntity;
		} else {
			ResponseEntity<String> responseEntity = new ResponseEntity<String>("entity with id" + id + "not found",
					HttpStatus.NOT_FOUND);
			return responseEntity;
		}

	}

	@PostMapping("dummy")
	public void dummy(@RequestBody List<Address> address) {

		System.out.println(address);

	}

	@DeleteMapping("delete/{id}")
	public ResponseEntity<? extends Object> deleteemp(@PathVariable("id") Integer empid) {
		System.out.println(empid);
		if (employeeRepositories.existsById(empid)) {
			employeeRepositories.deleteById(empid);

			return new ResponseEntity<String>("deleted", HttpStatus.OK);
		} else {

			return new ResponseEntity<String>("Entity not found" + empid, HttpStatus.OK);
		}

	}
}
