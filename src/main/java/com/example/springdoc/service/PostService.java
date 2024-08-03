package com.example.springdoc.service;

import java.util.List;

import com.example.springdoc.payload.PostDto;
import com.example.springdoc.payload.PostResponse;

public interface PostService {
	
	PostDto createPost(PostDto postDto, Integer userId, Integer postId);
	
	PostDto updatePost(PostDto postDto, Integer postId);
	
	void deletePost(Integer postId);
	
	PostDto getPostById(Integer postId);
	
	PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy);
	
	List<PostDto> getAllPostByCategory(Integer categoryId);
	
	List<PostDto> getAllPostByUser(Integer userId);
	
	List<PostDto> searchPostByKeyword(String keyword);
	
	List<PostDto> searchPostsTitleWithKewordContains(String keyword);

}