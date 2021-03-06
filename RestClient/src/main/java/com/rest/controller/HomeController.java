package com.rest.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.rest.helperclass.Employee;

@RestController
public class HomeController {

	@Autowired
	private volatile RestTemplate template;
	private static final String BaseUrl = "http://localhost:3333";
	
	//@Qualifier("samplefeignClient")
	//@LoadBalanced
/*	@Autowired
	private FiegnClient fiegnClient;*/
	
	

	@GetMapping(value="/getAllEmp")
	public List<Employee> getAllEmployess() {
		//template.getFor
		ResponseEntity<Employee[]> forEntity = template.getForEntity(BaseUrl + "/findAllEmployee", Employee[].class);
		System.out.println(forEntity);
		ResponseEntity<List<Employee>> exchange = template.exchange(BaseUrl + "/findAllEmployee", HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Employee>>() {
				});
		System.out.println(exchange);
		
		//List<Employee> employeesByFeignClient = fiegnClient.getEmployeesByFeignClient("julurufassak");

		//System.out.println(employeesByFeignClient+"employeesByFeignClient");
		return exchange.getBody();

	}

	@PostMapping("insertEmployee")
	public ResponseEntity<?> insertEmployee(@RequestBody Employee emp) {
		System.out.println(emp);

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.set("name", "juluru");
		HttpEntity<Employee> entity = new HttpEntity<Employee>(emp, headers);
		ResponseEntity<Employee> exchange = template.exchange(BaseUrl + "/insertEmployee", HttpMethod.POST, entity,
				Employee.class);
		// ResponseEntity<Employee> postForEntity =
		// template.postForEntity(BaseUrl + "/insertEmployee", emp,
		// Employee.class);
		System.out.println(exchange);
		return exchange;
	}

	@PutMapping("updateEmployee/{id}")
	
	public ResponseEntity<? extends Object> updateEmployee(@PathVariable(name = "id") Integer id,
			@RequestBody Employee emp) {

		Map<String, Integer> params = new HashMap();
		params.put("id", id);
		HttpEntity<Employee> empentity = new HttpEntity<Employee>(emp);
		ResponseEntity<Employee> exchange = template.exchange(BaseUrl + "/updateEmployee/{id}", HttpMethod.PUT,
				empentity, Employee.class, params);
		template.put(BaseUrl +"updateEmployee/{id}", emp, params);
		//exchange.get
		System.out.println(exchange);

		return exchange;
	}

}
