package com.example.springdoc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springdoc.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer>{

}