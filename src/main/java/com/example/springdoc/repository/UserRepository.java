package com.example.springdoc.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springdoc.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	//emai used for username - use email for username authentication
	Optional<User> findByEmail(String email);
}