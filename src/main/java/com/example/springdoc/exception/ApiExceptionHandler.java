package com.example.springdoc.exception;


public class ApiExceptionHandler extends RuntimeException{

	public ApiExceptionHandler() {
		super();
		
	}

	public ApiExceptionHandler(String message) {
		super(message);
		
	}
	
	
	

}