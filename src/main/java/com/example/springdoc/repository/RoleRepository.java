package com.example.springdoc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springdoc.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

}