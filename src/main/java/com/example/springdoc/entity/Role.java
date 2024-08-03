package com.example.springdoc.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Role {
	
	@Id
	private int id; 
	
	private String name;

}