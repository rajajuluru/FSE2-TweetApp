package com.juluru;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
	   @ExceptionHandler(ArithmeticException.class)
	    public ResponseEntity<Object> handleExceptions( Exception exception) {
	       
		   System.out.println("exception in ");
	        ResponseEntity<Object> entity = new ResponseEntity<Object>(exception.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
	        return entity;
	    }
}
