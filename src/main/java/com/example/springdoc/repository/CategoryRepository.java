package com.example.springdoc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springdoc.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

}