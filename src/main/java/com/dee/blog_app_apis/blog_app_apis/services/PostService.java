package com.dee.blog_app_apis.blog_app_apis.services;

import java.util.List;

import com.dee.blog_app_apis.blog_app_apis.payloads.PostDto;
import com.dee.blog_app_apis.blog_app_apis.payloads.PostResponse;

public interface PostService {

	public PostDto createPost(PostDto postDto, Integer user_id, Integer category_id);
	
	public PostDto updatePost(PostDto postDto, Integer post_id);
	
	public void deletePost(Integer post_id);
	
	public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy);
	
	public PostDto findPostById(Integer post_id);
	
	public List<PostDto>  getPostByUser(Integer user_id);
	
	public List<PostDto>  getPostByCategory(Integer category_id);
	
	public List<PostDto> searchPostByTitle(String keyword);
	//public List<PostDto> searchPosts(String keyword);
}
