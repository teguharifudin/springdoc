package com.example.springdoc.payload;

import lombok.Data;

@Data
public class JwtAuthenticationRequest {
	
	//here we consider email as username
	private String username;
	
	
	private String password;
	
}