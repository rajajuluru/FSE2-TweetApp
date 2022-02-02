package com.FlightBooking.jwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@SpringBootApplication

@Configuration
//@EnableAspectJAutoProxy
public class FlightBookingJWT {

	public static void main(String[] args) {
		SpringApplication.run(FlightBookingJWT.class, args);
	}
	
	@Bean
	public  NoOpPasswordEncoder passwordEncoder() {
	 return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
	}

}
