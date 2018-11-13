package com.jakub.quizgame.controller;

import com.jakub.quizgame.dto.CategoryDTO;
import com.jakub.quizgame.entity.Category;
import com.jakub.quizgame.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/category")
public class CategoryController {

	private CategoryService categoryService;

	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@GetMapping("/get")
	public ResponseEntity<Collection<CategoryDTO>> getCategories(){
		return new ResponseEntity<>(categoryService.getCategories(), HttpStatus.OK);
	}

	@GetMapping("/getWithQuestions")
	public ResponseEntity<Collection<CategoryDTO>> getCategoriesWithQuestions(){
		return new ResponseEntity<>(categoryService.getCategoriesWithQuestions(), HttpStatus.OK);
	}

}
