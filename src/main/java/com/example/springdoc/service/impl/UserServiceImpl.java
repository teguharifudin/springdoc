package com.example.springdoc.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.springdoc.exception.*;
import com.example.springdoc.utils.AppConstants;
import com.example.springdoc.entity.Role;
import com.example.springdoc.entity.User;
import com.example.springdoc.payload.UserDto;
import com.example.springdoc.repository.RoleRepository;
import com.example.springdoc.repository.UserRepository;
import com.example.springdoc.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired 
	private PasswordEncoder passwordEncoder;
	
	@Override
	public UserDto createUser(UserDto userDto) {
		User user = this.dtoToUser(userDto);
		User savedUser =this.userRepository.save(user);
		return this.userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		Optional<User> user = this.userRepository.findById(userId);
		if(user.isPresent()) {
			User userObj = new User();
			System.out.println("Inside userIsPresent");
			userObj.setId(userId);
			userObj.setName(userDto.getName());
			userObj.setEmail(userDto.getEmail());
			userObj.setPassword(userDto.getPassword());
			userObj.setAbout(userDto.getAbout());
			User updateUser = this.userRepository.save(userObj);
			UserDto userDtoObj = this.userToDto(updateUser);
			return userDtoObj;
		}else {
			throw new ResourceNotFoundException("User"," Id ",userId);

		}
	}

	@Override
	public UserDto getUserById(Integer userId) {
		User user = this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User"," Id ",userId));
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> usersList = this.userRepository.findAll();
		List<UserDto> userDtoList = usersList.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
		return userDtoList;
	}

	@Override
	public void deleteUser(Integer userId) {
		User user = this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", " Id ", userId));
		this.userRepository.delete(user);
		
	}
	
	public User dtoToUser(UserDto userDto) {
		User user = this.modelMapper.map(userDto, User.class);
		return user;
	}
	
	public UserDto userToDto(User user) {
		UserDto userDto = this.modelMapper.map(user, UserDto.class);

		return userDto;
	}

	@Override
	public UserDto registerNewUser(UserDto userDto) {
		User user = this.modelMapper.map(userDto, User.class);
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));
		Role role = this.roleRepository.findById(AppConstants.NORMAL_USER_ID_HARDCODED_ID_VALUE).get();
		user.getRoles().add(role);
		User newUser = this.userRepository.save(user);
		return this.modelMapper.map(newUser, UserDto.class);
	}

}