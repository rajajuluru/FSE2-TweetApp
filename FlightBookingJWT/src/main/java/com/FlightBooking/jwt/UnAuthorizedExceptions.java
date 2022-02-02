package com.FlightBooking.jwt;

import org.springframework.stereotype.Component;

@Component
public class UnAuthorizedExceptions extends RuntimeException {
	
	public UnAuthorizedExceptions(String s)
	{
		super(s);
	}
	
	public UnAuthorizedExceptions()
	{
		super();
	}

}
