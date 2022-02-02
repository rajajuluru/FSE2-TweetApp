package com.juluru;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
//@EnableGlobalMethodSecurity
public class SpringBootSecurityFormBasedAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSecurityFormBasedAuthApplication.class, args);
	}

}
