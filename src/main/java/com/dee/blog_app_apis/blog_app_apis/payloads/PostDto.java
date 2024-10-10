package com.dee.blog_app_apis.blog_app_apis.payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.dee.blog_app_apis.blog_app_apis.payloads.UserDto;
import com.dee.blog_app_apis.blog_app_apis.entities.Comments;
import com.dee.blog_app_apis.blog_app_apis.payloads.CategoryDto;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;

public class PostDto {
	
	private int postId;
	
	@NotEmpty
	private String title;
	
	@NotEmpty
	private String content;	
	
	@NotEmpty
	private String imageName;
	
	private Date addedDate;
	
	private UserDto user;
	
	private CategoryDto category;
	
	private Set<CommentDto> comment = new HashSet<CommentDto>();
	
	public PostDto() {
	}

	public PostDto(int postId, @NotEmpty String title, @NotEmpty String content, @NotEmpty String imageName,
			Date addedDate, UserDto user, CategoryDto category, Set<CommentDto> comment) {
		super();
		this.postId = postId;
		this.title = title;
		this.content = content;
		this.imageName = imageName;
		this.addedDate = addedDate;
		this.user = user;
		this.category = category;
		this.comment = comment;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public Date getAddedDate() {
		return addedDate;
	}

	public void setAddedDate(Date addedDate) {
		this.addedDate = addedDate;
	}

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	public CategoryDto getCategory() {
		return category;
	}

	public void setCategory(CategoryDto category) {
		this.category = category;
	}

	public Set<CommentDto> getComment() {
		return comment;
	}

	public void setComment(Set<CommentDto> comment) {
		this.comment = comment;
	}

	
	
	
}
