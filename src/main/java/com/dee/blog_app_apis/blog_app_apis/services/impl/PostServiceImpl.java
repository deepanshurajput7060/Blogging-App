package com.dee.blog_app_apis.blog_app_apis.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Sort;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dee.blog_app_apis.blog_app_apis.entities.Category;
import com.dee.blog_app_apis.blog_app_apis.entities.Post;
import com.dee.blog_app_apis.blog_app_apis.entities.User;
import com.dee.blog_app_apis.blog_app_apis.exceptions.ResourceNotFoundException;
import com.dee.blog_app_apis.blog_app_apis.payloads.PostDto;
import com.dee.blog_app_apis.blog_app_apis.payloads.PostResponse;
import com.dee.blog_app_apis.blog_app_apis.repositories.CategoryRepo;
import com.dee.blog_app_apis.blog_app_apis.repositories.PostRepo;
import com.dee.blog_app_apis.blog_app_apis.repositories.UserRepo;
import com.dee.blog_app_apis.blog_app_apis.services.PostService;

@Service
public class PostServiceImpl implements PostService{

	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Override
	public PostDto createPost(PostDto postDto, Integer user_id, Integer category_id) {
		
		User user = userRepo.findById(user_id)
								.orElseThrow(() -> new ResourceNotFoundException("user", "user_id", user_id));
		
		Category cat = categoryRepo.findById(category_id)
				.orElseThrow(() -> new ResourceNotFoundException("category", "category_id", category_id));
		
		System.out.println(cat.toString());
		Post post = modelMapper.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(cat);
		
		Post newPost = postRepo.save(post);
		return modelMapper.map(newPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer post_id) {
		
		Post post = postRepo.findById(post_id)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "Post_id", post_id));
		
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());
		
		Post updatedpost = postRepo.save(post);
		return modelMapper.map(updatedpost, PostDto.class);
	}

	@Override
	public void deletePost(Integer post_id) {
		
		Post post = postRepo.findById(post_id)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "Post_id", post_id));
		
		postRepo.delete(post);
	}

	@Override
	public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy) {
		
		Pageable p = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
		Page<Post> PagePosts = postRepo.findAll(p);
		
		List<Post> posts = PagePosts.getContent();
		
		List<PostDto> postdtos = posts.stream()
				.map((post) -> modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		
		PostResponse postResponse = new PostResponse();
		postResponse.setContent(postdtos);
		postResponse.setPageNumber(PagePosts.getNumber());
		postResponse.setPageSize(PagePosts.getSize());
		postResponse.setTotalElement(PagePosts.getTotalElements());
		postResponse.setTotalPages(PagePosts.getTotalPages());
		postResponse.setLastPage(PagePosts.isLast());
		
		return postResponse;
	}

	@Override
	public PostDto findPostById(Integer post_id) {
		
		Post post = postRepo.findById(post_id)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "Post_id", post_id));
		
		return modelMapper.map(post, PostDto.class);
	}

	@Override
	public List<PostDto> getPostByUser(Integer user_id) {
		User user = userRepo.findById(user_id)
				.orElseThrow(() -> new ResourceNotFoundException("user", "user_id", user_id));
		
		List<Post> posts = postRepo.findByUser(user);
		
		List<PostDto> postdtos = posts.stream()
				.map((post) -> modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		
		return postdtos;
	}

	@Override
	public List<PostDto> getPostByCategory(Integer category_id) {
		
		Category cat = categoryRepo.findById(category_id)
				.orElseThrow(() -> new ResourceNotFoundException("category", "category_id", category_id));
		
		List<Post> posts = postRepo.findByCategory(cat);
		
		List<PostDto> postdtos = posts.stream()
				.map((post) -> modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		
		return postdtos;
	}

	@Override
	public List<PostDto> searchPostByTitle(String keyword) {
		List<Post> posts = postRepo.searchPostByTitle(keyword);
		
		List<PostDto> postdtos = posts.stream()
				.map((post) -> modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		
		return postdtos;
	}

}
























