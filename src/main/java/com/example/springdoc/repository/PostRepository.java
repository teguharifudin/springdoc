package com.example.springdoc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springdoc.entity.Category;
import com.example.springdoc.entity.Post;
import com.example.springdoc.entity.User;


public interface PostRepository extends JpaRepository<Post, Integer>{
	
	List<Post> findByUser(User user);
	
	List<Post> findByCategory(Category category);
	
	List<Post> findByPostTitle(String keyword);
	
	List<Post> findByPostTitleContaining(String keyword);

}