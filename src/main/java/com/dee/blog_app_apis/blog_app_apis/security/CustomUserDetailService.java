package com.dee.blog_app_apis.blog_app_apis.security;

import org.hibernate.annotations.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.dee.blog_app_apis.blog_app_apis.entities.User;
import com.dee.blog_app_apis.blog_app_apis.exceptions.ResourceNotFoundException;
import com.dee.blog_app_apis.blog_app_apis.repositories.UserRepo;

@Component
public class CustomUserDetailService implements UserDetailsService{

	@Autowired
	private UserRepo userRepo;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		// loading user from database by username
		User user = userRepo.findByEmail(username)
					.orElseThrow(() -> new ResourceNotFoundException("user", "Email" + username, 0));
				
		return user;
	}

}
