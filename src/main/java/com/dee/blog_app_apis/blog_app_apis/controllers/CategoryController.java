package com.dee.blog_app_apis.blog_app_apis.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dee.blog_app_apis.blog_app_apis.payloads.ApiResponse;
import com.dee.blog_app_apis.blog_app_apis.payloads.CategoryDto;
import com.dee.blog_app_apis.blog_app_apis.services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;

	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto) {		
		CategoryDto catDto  = categoryService.createCategory(categoryDto);
		return new ResponseEntity<CategoryDto> (catDto, HttpStatus.CREATED);
		
	}
	
	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoryDto>  updateCategory(@Valid @RequestBody CategoryDto categoryDto,@PathVariable Integer categoryId){
		CategoryDto catDto = categoryService.updateCategory(categoryDto, categoryId);
		return new ResponseEntity<CategoryDto> (catDto, HttpStatus.OK);
		
	}
	
	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDto>  getCategoryById(@PathVariable Integer categoryId){
		CategoryDto catDto = categoryService.getCategoryById(categoryId);
		return new ResponseEntity<CategoryDto> (catDto, HttpStatus.OK);
		
	}
	
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAllCategory(){
		List<CategoryDto> allCatDto = categoryService.getAllCategory();
		return new ResponseEntity<List<CategoryDto>> (allCatDto, HttpStatus.OK);
		
	}
	
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer categoryId) {
		categoryService.deleteCategory(categoryId);
		return new ResponseEntity<ApiResponse> ((new ApiResponse("Category Deleted successfully  ", true)), HttpStatus.OK);
	}
}
