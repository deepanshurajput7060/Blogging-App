package com.dee.blog_app_apis.blog_app_apis.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dee.blog_app_apis.blog_app_apis.entities.Category;
import com.dee.blog_app_apis.blog_app_apis.entities.Post;
import com.dee.blog_app_apis.blog_app_apis.entities.User;

@Repository
public interface PostRepo extends JpaRepository<Post, Integer>{

	List<Post> findByUser(User user);
	
	List<Post> findByCategory(Category category);
	
	@Query ("select p from Post p where p.title like :key")
	List<Post> searchPostByTitle(@Param("key") String title);
	//List<Post> findByTitleContaining(String title);
}
