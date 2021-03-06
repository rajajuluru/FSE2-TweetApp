package com.FlightBooking.jwt.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.FlightBooking.jwt.model.UserCandidate;

@Service
public class ServiceUtilities {

	public UserCandidate getUserPasswordFrmDB(String username) {
		System.out.println(username + "username");

		List<UserCandidate> userCandidateDetails = ServiceUtilities.getUserCandidateDetails();
		Optional<UserCandidate> findFirst = userCandidateDetails.stream()
				.filter(details -> details.getUsername().equals(username)).findFirst();
		return findFirst.get();
	}

	public static List<UserCandidate> getUserCandidateDetails() {
		UserCandidate raja = new UserCandidate("raja", "raja");
		UserCandidate tom = new UserCandidate("tom", "tom");
		UserCandidate admin = new UserCandidate("admin", "admin");
		List<UserCandidate> usersdata = new ArrayList<>();
		usersdata.add(raja);
		usersdata.add(tom);

		usersdata.add(admin);
		return usersdata;

	}

}
