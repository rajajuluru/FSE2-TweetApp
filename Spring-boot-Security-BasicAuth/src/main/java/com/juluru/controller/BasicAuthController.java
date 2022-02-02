package com.juluru.controller;

import java.util.function.Consumer;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BasicAuthController {


	@GetMapping("/hello")
	@PreAuthorize("hasRole('STUDENT')")
	public String hellocontroller()
	{
	
		return "hello Basic Authentication";
		
	}
}
