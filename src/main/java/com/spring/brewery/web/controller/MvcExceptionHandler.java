package com.spring.brewery.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class MvcExceptionHandler {
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<List<String>> validationErrorHandler(ConstraintViolationException ex){
		List<String> errors = new ArrayList<String>(ex.getConstraintViolations().size());
		
		ex.getConstraintViolations().forEach(constraintVioltion->{
			errors.add(constraintVioltion.getPropertyPath()+" : "+constraintVioltion.getMessage());
		});
		
		return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<String> methodArgumentTypeMismatchHandler(MethodArgumentTypeMismatchException ex){
		
		
		return new ResponseEntity<>(ex.getCause().getMessage(),HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(BindException.class)
	public ResponseEntity<String> BindExceptionHandler(BindException ex){
		
		
		return new ResponseEntity<>(ex.getCause().getMessage(),HttpStatus.BAD_REQUEST);
		
	}


}
