package com.dee.blog_app_apis.blog_app_apis.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dee.blog_app_apis.blog_app_apis.config.AppConstants;
import com.dee.blog_app_apis.blog_app_apis.entities.Post;
import com.dee.blog_app_apis.blog_app_apis.payloads.ApiResponse;
import com.dee.blog_app_apis.blog_app_apis.payloads.PostDto;
import com.dee.blog_app_apis.blog_app_apis.payloads.PostResponse;
import com.dee.blog_app_apis.blog_app_apis.services.PostService;

@RestController
@RequestMapping("/api/")
public class postController {
	
	@Autowired
	private PostService postService;

	@PostMapping ("user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(
			@RequestBody PostDto postDto,
			@PathVariable Integer userId,
			@PathVariable Integer categoryId
			) 
	{
		PostDto createdPost = postService.createPost(postDto, userId, categoryId);		
		return new ResponseEntity<PostDto>(createdPost, HttpStatus.CREATED);
		
	}
	
	@PutMapping("posts/{post_id}")
	public ResponseEntity<PostDto> updatePost(
			@RequestBody PostDto postDto,
			@PathVariable Integer post_id) 
	{
		PostDto updatedPost  = postService.updatePost(postDto, post_id);
		return new ResponseEntity<PostDto> (updatedPost, HttpStatus.OK);
		
	}
	
	@DeleteMapping("posts/{post_id}")
	public ApiResponse deletePost(@PathVariable Integer post_id) {
		postService.deletePost(post_id);
		return new ApiResponse("data is deleted successfully", true);
	}
	
	@GetMapping ("posts")
	public ResponseEntity<PostResponse> getAllPost(
			@RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber, 
			@RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy)
	{
		PostResponse postResponse = postService.getAllPost(pageNumber, pageSize, sortBy);
		return new ResponseEntity<PostResponse> (postResponse, HttpStatus.OK);
		
	}
	
	@GetMapping ("/posts/{post_id}")
	public ResponseEntity<PostDto> findPostById(@PathVariable Integer post_id) {
		PostDto post = postService.findPostById(post_id);
		return new ResponseEntity<PostDto> (post, HttpStatus.OK);
		
	}
	
	@GetMapping("user/{user_id}/posts")
	public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer user_id) {
		
		List<PostDto> posts = postService.getPostByUser(user_id);
		
		return new ResponseEntity<List<PostDto>> (posts, HttpStatus.OK);
		
	}
	
	@GetMapping("category/{category_id}/posts")
	public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer category_id) {
		
		List<PostDto> posts = postService.getPostByCategory(category_id);
		
		return new ResponseEntity<List<PostDto>> (posts, HttpStatus.OK);
		
	}
	
	@GetMapping("posts/search/{keywords}")
	public ResponseEntity<List<PostDto>> searchPostsByTitle(@PathVariable String keywords) {
		List<PostDto> result = postService.searchPostByTitle("%" + keywords + "%");
		return new ResponseEntity<List<PostDto>> (result, HttpStatus.OK);
		
	}
}
























