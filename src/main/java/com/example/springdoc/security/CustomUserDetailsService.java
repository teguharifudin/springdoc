package com.example.springdoc.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.springdoc.entity.User;
import com.example.springdoc.exception.ResourceNotFoundException;
import com.example.springdoc.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = this.userRepository.findByEmail(username).orElseThrow(()->new ResourceNotFoundException("User ", " email: "+username, 0));
		
		return user;
	}

}