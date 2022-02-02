package com.juluru.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FormBasesAuthController {


	@GetMapping("/hello")
	@PreAuthorize("hasRole('STUDENT')")
	public String hellocontroller()
	{
		
		
		return "hello Basic Authentication";
		
	}
	
	@GetMapping("/")
	//@PreAuthorize("hasRole('STUDENT')")
	public String formbased()
	{
		return "spring boot formbased";
		
	}
}
