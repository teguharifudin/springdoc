package com.example.springdoc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springdoc.payload.ApiResponse;
import com.example.springdoc.payload.UserDto;
import com.example.springdoc.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserRestController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
		UserDto createdUserDto = this.userService.createUser(userDto);
		return new ResponseEntity<>(createdUserDto,HttpStatus.CREATED);
		
	}
	
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable Integer userId){
		UserDto updatedUser = this.userService.updateUser(userDto, userId);
		return  ResponseEntity.ok(updatedUser);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Integer id) {
		this.userService.deleteUser(id);
		return new ResponseEntity<ApiResponse>(new ApiResponse("User Deleted Successfully.", true), HttpStatus.OK);// or
	}
	
	@GetMapping("")
	public ResponseEntity<List<UserDto>> getAllUsers(){
		List<UserDto> allUsersList = this.userService.getAllUsers();
		return ResponseEntity.ok(allUsersList);//or
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getSingleUser(@PathVariable Integer userId){
		UserDto user = this.userService.getUserById(userId);
		return ResponseEntity.ok(user);//or
	}

}