package com.rest.controller.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.EnableLoadTimeWeaving;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.rest.helperclass.Employee;

@FeignClient//(name="client")
@Component
public interface FiegnClient {
	 static final String BaseUrl = "http://localhost:3333";
	
	  @GetMapping(BaseUrl+"/findAllEmployee")
	   public List<Employee> getEmployeesByFeignClient(@RequestHeader("name") String name);
	

}
