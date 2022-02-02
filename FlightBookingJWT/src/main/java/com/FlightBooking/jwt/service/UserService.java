package com.FlightBooking.jwt.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.FlightBooking.jwt.UserNotFoundException;
import com.FlightBooking.jwt.model.UserCandidate;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	RestTemplate template;
	@Autowired
	ServiceUtilities serviceUtilities;
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {


    	UserCandidate userPasswordFrmDB=null;
 
    	//Logic to get the user form the Database
    	

	userPasswordFrmDB= serviceUtilities.getUserPasswordFrmDB(userName);
	if(null==userPasswordFrmDB || userPasswordFrmDB.getPassword().isEmpty())
	{
		
	}
	else
	{
		
		new UserNotFoundException("user not found exception");
	}
	   return new User(userPasswordFrmDB.getUsername(),userPasswordFrmDB.getPassword(),new ArrayList<>());
	
}


     

        }

