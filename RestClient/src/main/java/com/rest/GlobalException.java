package com.rest;

import java.net.SocketTimeoutException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalException extends ResponseEntityExceptionHandler {

	@ExceptionHandler(SocketTimeoutException.class)
	public ResponseEntity<Object> handleExceptions(Exception exception) {

		System.out.println("exception in ");
		ResponseEntity<Object> entity = new ResponseEntity<Object>("Read timeout Exception",
				HttpStatus.GATEWAY_TIMEOUT);
		return entity;
	}
}
