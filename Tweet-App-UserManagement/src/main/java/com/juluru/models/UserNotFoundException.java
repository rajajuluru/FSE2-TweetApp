package com.juluru.models;

import org.springframework.stereotype.Component;

@Component
public class UserNotFoundException extends Exception{

	public UserNotFoundException(String s)
	{
		super(s);
	}
	public UserNotFoundException()
	{
		super();
	}
}
