package com.dee.blog_app_apis.blog_app_apis.services.impl;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dee.blog_app_apis.blog_app_apis.entities.Category;
import com.dee.blog_app_apis.blog_app_apis.exceptions.ResourceNotFoundException;
import com.dee.blog_app_apis.blog_app_apis.payloads.CategoryDto;
import com.dee.blog_app_apis.blog_app_apis.repositories.CategoryRepo;
import com.dee.blog_app_apis.blog_app_apis.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category category = modelMapper.map(categoryDto, Category.class);
		Category addedCat = categoryRepo.save(category);
		return modelMapper.map(addedCat, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		
		Category cat = categoryRepo.findById(categoryId)
								.orElseThrow(() -> new ResourceNotFoundException("category", "categoryId", categoryId));
		
		cat.setCategoryTitle(categoryDto.getCategoryTitle());
		cat.setCategoryDescription(categoryDto.getCategoryDescription());
		
		Category updatedCat = categoryRepo.save(cat);
		
		return modelMapper.map(updatedCat, CategoryDto.class);
	}

	@Override
	public CategoryDto getCategoryById(Integer categoryId) {
		Category cat = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("category", "categoryId", categoryId));

		return modelMapper.map(cat, CategoryDto.class);
	}
	
	@Override
	public List<CategoryDto> getAllCategory() {
		List<Category> allCat = categoryRepo.findAll();
		
		List<CategoryDto> allCatDto = allCat.stream()
											.map(category -> modelMapper.map(category, CategoryDto.class))
											.collect(Collectors.toList());
		return allCatDto;
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		Category cat = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("category", "categoryId", categoryId));
		
		categoryRepo.delete(cat);
	}

	
	
//	public CategoryDto categoryToDto(Category category) {
//		CategoryDto categoryDto = modelMapper.map(category, CategoryDto.class);
//		return categoryDto;
//	}
//	
//	public Category dtoToCategory(CategoryDto categoryDto) {
//		Category category = modelMapper.map(categoryDto, Category.class);
//		return category;
//	}

}
