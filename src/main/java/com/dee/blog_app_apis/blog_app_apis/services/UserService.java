package com.dee.blog_app_apis.blog_app_apis.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dee.blog_app_apis.blog_app_apis.payloads.UserDto;


public interface UserService {

	UserDto createUser(UserDto userDto);
	
	UserDto updateUser(UserDto userDto, Integer id);
	
	UserDto getUserById(Integer userId);
	
	List<UserDto> getAllUsers();
	
	void deleteUser(Integer userId);
	
}
