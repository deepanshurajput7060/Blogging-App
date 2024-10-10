package com.dee.blog_app_apis.blog_app_apis.services;

import java.util.List;

import com.dee.blog_app_apis.blog_app_apis.payloads.CategoryDto;

public interface CategoryService {

	public CategoryDto createCategory(CategoryDto categoryDto);
	
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);
	
	public CategoryDto getCategoryById(Integer categoryId);
	
	public List<CategoryDto> getAllCategory();
	
	public void deleteCategory(Integer categoryId);
}
