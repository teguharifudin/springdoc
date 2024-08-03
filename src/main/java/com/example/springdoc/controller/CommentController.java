package com.example.springdoc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springdoc.payload.ApiResponse;
import com.example.springdoc.payload.CommentDto;
import com.example.springdoc.service.CommentService;

@RestController
@RequestMapping("/api/")
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	@PostMapping("/post/{postId}/comments")
	public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto, @PathVariable("postId") Integer postId){
		
		CommentDto createComment = this.commentService.createComment(commentDto, postId);
		
		return new ResponseEntity<CommentDto>(createComment,HttpStatus.CREATED);
		
	}
	
	@DeleteMapping("/delete/comments/{commentId}")
	public ResponseEntity<ApiResponse> deleteComment(@PathVariable("commentId") Integer commentId){
		
		this.commentService.deleteComment(commentId);
		
		return new ResponseEntity<ApiResponse>(new ApiResponse("Successfully deleted !!",true),HttpStatus.OK);
		
	}

}