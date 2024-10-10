package com.dee.blog_app_apis.blog_app_apis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dee.blog_app_apis.blog_app_apis.entities.Comments;

@Repository
public interface CommentRepo extends JpaRepository<Comments, Integer>{

}
