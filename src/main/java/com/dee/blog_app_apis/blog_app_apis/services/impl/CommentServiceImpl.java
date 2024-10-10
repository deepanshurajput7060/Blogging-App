package com.dee.blog_app_apis.blog_app_apis.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dee.blog_app_apis.blog_app_apis.entities.Comments;
import com.dee.blog_app_apis.blog_app_apis.entities.Post;
import com.dee.blog_app_apis.blog_app_apis.exceptions.ResourceNotFoundException;
import com.dee.blog_app_apis.blog_app_apis.payloads.CommentDto;
import com.dee.blog_app_apis.blog_app_apis.repositories.CommentRepo;
import com.dee.blog_app_apis.blog_app_apis.repositories.PostRepo;
import com.dee.blog_app_apis.blog_app_apis.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService{

	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private CommentRepo commentRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {

		Post post = postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));
		
		Comments comments = modelMapper.map(commentDto, Comments.class);
		
		comments.setPost(post);
		
		Comments savedComments = commentRepo.save(comments);
		
		return modelMapper.map(savedComments, CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentId) {

		Comments com = commentRepo.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("Comments", "commentId", commentId));
		commentRepo.delete(com);
		
	}

}
