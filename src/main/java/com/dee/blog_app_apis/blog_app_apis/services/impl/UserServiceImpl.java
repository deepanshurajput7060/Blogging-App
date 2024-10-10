package com.dee.blog_app_apis.blog_app_apis.services.impl;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dee.blog_app_apis.blog_app_apis.entities.User;
import com.dee.blog_app_apis.blog_app_apis.exceptions.ResourceNotFoundException;
import com.dee.blog_app_apis.blog_app_apis.payloads.UserDto;
import com.dee.blog_app_apis.blog_app_apis.repositories.UserRepo;
import com.dee.blog_app_apis.blog_app_apis.services.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDto createUser(UserDto userDto) {
		User user = dtoToUser(userDto);
		User savedUser = userRepo.save(user);
		
		return userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer id) {
		
		User user = userRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", id));
		
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		
		User updatedUser = userRepo.save(user);
		
		return userToDto(updatedUser);
	}

	@Override
	public UserDto getUserById(Integer userId) {
		
		User user = userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		
		return userToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		
		List<User> users = userRepo.findAll();
		
		List<UserDto> userDtos = users.stream()
									.map(user -> userToDto(user))
									.collect(Collectors.toList());
		
		return userDtos;
	}

	@Override
	public void deleteUser(Integer userId) {
		
		User user = userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		userRepo.delete(user);
	}
	
	
	private User dtoToUser(UserDto userDto) {
		User user = modelMapper.map(userDto, User.class);
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setAbout(userDto.getAbout());
//		user.setPassword(userDto.getPassword());		
		return user;		
	}
	
	public UserDto userToDto(User user) {
		UserDto userDto = modelMapper.map(user, UserDto.class);
//		userDto.setId(user.getId());
//		userDto.setName(user.getName());
//		userDto.setEmail(user.getEmail());
//		userDto.setAbout(user.getAbout());
//		userDto.setPassword(user.getPassword());
		return userDto;		
	}

}
