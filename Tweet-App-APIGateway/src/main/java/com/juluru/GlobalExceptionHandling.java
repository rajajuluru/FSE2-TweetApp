package com.juluru;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.juluru.models.JwtResponse;

@ControllerAdvice
@Component
public class GlobalExceptionHandling extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { JwtCustomException.class })
	public ResponseEntity<Object> handleExceptions(JwtCustomException exception, WebRequest webRequest) {
		JwtResponse response = new JwtResponse(exception.getMessage(), false);
		System.out.println("JwtCustomException called");
		ResponseEntity<Object> entity = new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		return entity;
	}

}
