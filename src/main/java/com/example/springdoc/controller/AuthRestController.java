package com.example.springdoc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springdoc.exception.ApiExceptionHandler;
import com.example.springdoc.payload.JwtAuthenticationRequest;
import com.example.springdoc.payload.JwtAuthenticationResponse;
import com.example.springdoc.payload.UserDto;
import com.example.springdoc.security.JwtTokenHelper;
import com.example.springdoc.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthRestController {
	
	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/login")
	public ResponseEntity<JwtAuthenticationResponse> createToken(@RequestBody JwtAuthenticationRequest request) throws Exception{
		this.authenticate(request.getUsername(), request.getPassword());
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());
		String ourGeneratedToken = this.jwtTokenHelper.generateToken(userDetails);
		JwtAuthenticationResponse response = new JwtAuthenticationResponse();
		response.setToken(ourGeneratedToken);
		return new ResponseEntity<JwtAuthenticationResponse>(response, HttpStatus.OK);
	}

	private void authenticate(String username, String password) throws Exception {
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password);
		System.out.println(usernamePasswordAuthenticationToken);
		try {	
			this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);
		} catch (BadCredentialsException e) {
			System.out.println("Invalid Username or Password!");
			throw new ApiExceptionHandler("Invalid Username or Password!");
		}
	}
	
	@PostMapping("/register")
	public ResponseEntity<UserDto> registerNewUser(@RequestBody UserDto userDto){
		UserDto registeredNewUser = this.userService.registerNewUser(userDto);
		return new ResponseEntity<UserDto>(registeredNewUser, HttpStatus.CREATED);
	}
}