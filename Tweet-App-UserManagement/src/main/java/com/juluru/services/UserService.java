package com.juluru.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.juluru.models.RegisterHelperClass;
import com.juluru.models.UserNotFoundException;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	RestTemplate template;
	@Autowired
	ServiceUtilities serviceUtilities;
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {


    	RegisterHelperClass userPasswordFrmDB=null;
 
    	//Logic to get the user form the Database
    	

	userPasswordFrmDB= serviceUtilities.getUserPasswordFrmDB(userName);
	if(null==userPasswordFrmDB || userPasswordFrmDB.getPassword().isEmpty())
	{
		
	}
	else
	{
		
		new UserNotFoundException("user not found exception");
	}
	   return new User(userPasswordFrmDB.getEmail(),userPasswordFrmDB.getPassword(),new ArrayList<>());
	
}


     

        }

