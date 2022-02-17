package com.juluru;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
public class TweetAppUserManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(TweetAppUserManagementApplication.class, args);
	}
	
	@Bean
	public  NoOpPasswordEncoder passwordEncoder() {
	 return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
	

}

}
