package com.dee.blog_app_apis.blog_app_apis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dee.blog_app_apis.blog_app_apis.entities.Comments;
import com.dee.blog_app_apis.blog_app_apis.payloads.ApiResponse;
import com.dee.blog_app_apis.blog_app_apis.payloads.CommentDto;
import com.dee.blog_app_apis.blog_app_apis.services.CommentService;

@RestController
@RequestMapping ("/api")
public class CommentController {

	@Autowired
	private CommentService commentService;
	
	@PostMapping ("/post/{postId}/comments")
	public ResponseEntity<CommentDto> createComment(
			@RequestBody CommentDto commentDto,
			@PathVariable Integer postId)
	{
		
		CommentDto createComment = commentService.createComment(commentDto, postId);
		return new ResponseEntity<CommentDto> (createComment, HttpStatus.CREATED);
		
	}
	
	@DeleteMapping ("/comments/{commentId}")
	public ResponseEntity<ApiResponse> createComment(
			@PathVariable Integer commentId)
	{
		commentService.deleteComment(commentId);
		
		ApiResponse res = new ApiResponse("comment deleted successfully.", true);
		
		return new ResponseEntity<ApiResponse> (res, HttpStatus.OK);
		
	}
}

















