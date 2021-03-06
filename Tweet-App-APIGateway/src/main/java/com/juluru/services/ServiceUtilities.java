package com.juluru.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.juluru.models.RegisterHelperClass;

@Service
public class ServiceUtilities {

	private final String baseurl = "http://localhost:9999";
	@Autowired
	private LoadBalancerClient loadBalancerClient;

	public String getEurkaClientBaseUriofBookingServiceMain() {
		ServiceInstance serviceInstance = loadBalancerClient.choose("tweetapp");
		return serviceInstance.getUri().toString();
	}
	
	public String getEurkaClientBaseUriofUserManagement() {
		ServiceInstance serviceInstance = loadBalancerClient.choose("usermanagement");
		return serviceInstance.getUri().toString();
	}

	public RegisterHelperClass getUserPasswordFrmDB(String username) {
		System.out.println(username + "username");
		RestTemplate template = new RestTemplate();
		System.out.println(getEurkaClientBaseUriofBookingServiceMain()+"sagdghashgdgh");
		ResponseEntity<RegisterHelperClass> exchange = template.exchange(
				getEurkaClientBaseUriofBookingServiceMain()+"/api/v1.0/tweets/getUserDetails/" + username, HttpMethod.GET, null,
				RegisterHelperClass.class);
		System.out.println(exchange.getStatusCodeValue());
		System.out.println(exchange.getBody());
		return exchange.getBody();
	}

}
