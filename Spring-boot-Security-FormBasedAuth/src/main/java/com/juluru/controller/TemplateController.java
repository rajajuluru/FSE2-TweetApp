package com.juluru.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TemplateController {
	
	@GetMapping("/login")
	public String loginpage()
	{
		return "login";
	}
	
	@GetMapping("/success")
	public String success()
	{
		return "success";
	}

}
