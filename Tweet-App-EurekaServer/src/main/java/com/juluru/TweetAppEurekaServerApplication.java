package com.juluru;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class TweetAppEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TweetAppEurekaServerApplication.class, args);
	}

}
