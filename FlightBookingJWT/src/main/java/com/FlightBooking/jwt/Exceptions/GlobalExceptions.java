package com.FlightBooking.jwt.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.FlightBooking.jwt.UnAuthorizedExceptions;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;

@ControllerAdvice
@Component
//@RestController
public class GlobalExceptions extends ResponseEntityExceptionHandler {
	@ExceptionHandler(value = { ExpiredJwtException.class })
	public ResponseEntity<String> handleIOException() {
		return new ResponseEntity<String>("JWT token is expired", HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler(value = { MalformedJwtException.class })
	public ResponseEntity<String> JsonParseException() {
		return new ResponseEntity<String>("invalid format of JWT token", HttpStatus.BAD_GATEWAY);
	}
	
	
	
	//@GetMapping("/access")
	@ExceptionHandler(value = { UnAuthorizedExceptions.class })
	public ResponseEntity<String> UnAuthorizedExceptions() {
		System.out.println("in exception handler");
		return new ResponseEntity<String>("unAuthorized Access of resources", HttpStatus.NON_AUTHORITATIVE_INFORMATION);
	}
}
