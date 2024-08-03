package com.example.springdoc.service;

import com.example.springdoc.payload.CommentDto;

public interface CommentService {
	
	CommentDto createComment(CommentDto commentDto, Integer postId);
	
	void deleteComment(Integer commentId);

}