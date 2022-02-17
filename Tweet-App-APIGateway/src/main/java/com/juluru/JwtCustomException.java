package com.juluru;

import org.springframework.stereotype.Component;

@Component
public class JwtCustomException extends RuntimeException{
	
	public JwtCustomException(String m){
		super(m);
	}
	public JwtCustomException(){
		super();
	}

}
