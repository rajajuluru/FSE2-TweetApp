package com.rest;

import java.time.Duration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
//@EnableFeignClients(basePackageClasses=".FiegnClient.class")
@ComponentScan({"com.rest.controller.service"})

public class RestClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestClientApplication.class, args);
	}


	@Bean
	public RestTemplate template(RestTemplateBuilder restTemplateBuilder)
	{
		RestTemplate build = restTemplateBuilder.setReadTimeout(Duration.ofMillis(1000)).build();
		return build;
	}
}
