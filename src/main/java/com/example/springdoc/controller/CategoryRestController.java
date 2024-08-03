package com.example.springdoc.controller;

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

import com.example.springdoc.payload.ApiResponse;
import com.example.springdoc.payload.CategoryDto;
import com.example.springdoc.service.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categories")
public class CategoryRestController {
	
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
		System.out.println("Inside Create Cat");
		CategoryDto createdCategory = this.categoryService.createCategory(categoryDto);
		return new ResponseEntity<CategoryDto>(createdCategory, HttpStatus.CREATED);
	}
	
	@PutMapping("/{catId}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable("catId") Integer catId){
		CategoryDto updateCategory = this.categoryService.updateCategory(categoryDto, catId);
		return new ResponseEntity<CategoryDto>(updateCategory, HttpStatus.OK);
	}
	
	@DeleteMapping("/{catId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable("catId") Integer catId){
		this.categoryService.deleteCategory(catId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Category Deleted Successfully!", true), HttpStatus.OK);
	}
	
	@GetMapping("/{catId}")
	public ResponseEntity<CategoryDto> getCategory(@PathVariable("catId") Integer catId){
		CategoryDto category = this.categoryService.getCategory(catId);
		return new ResponseEntity<CategoryDto>(category, HttpStatus.OK);
	}
	
	@GetMapping("")
	public ResponseEntity<List<CategoryDto>> getAllCategory(){
		List<CategoryDto> categories = this.categoryService.getAllCategory();
		return new ResponseEntity<List<CategoryDto>>(categories, HttpStatus.OK);
	}

}