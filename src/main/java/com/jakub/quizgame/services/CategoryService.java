package com.jakub.quizgame.services;

import com.jakub.quizgame.dto.CategoryDTO;
import com.jakub.quizgame.exceptions.NotFoundException;
import com.jakub.quizgame.repository.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CategoryService {

	private CategoryRepository categoryRepository;
	private QuestionService questionService;

	public CategoryService(CategoryRepository categoryRepository, QuestionService questionService) {
		this.categoryRepository = categoryRepository;
		this.questionService = questionService;
	}

	public Collection<CategoryDTO> getCategoriesWithQuestions() {
		return categoryRepository.findAll()
				.stream()
				.map(c -> new ModelMapper().map(c, CategoryDTO.class))
				.filter(Objects::nonNull)
				.filter(c -> {
					try {
						return questionService.getQuestionsByCategory(c.getId()).size() >= 5;
					} catch (NotFoundException e) {
						return Boolean.parseBoolean(null);
					}
				})
				.collect(Collectors.toList());
	}

	public Collection<CategoryDTO> getCategories() {
		return categoryRepository.findAll()
				.stream()
				.map(c -> new ModelMapper().map(c, CategoryDTO.class))
				.collect(Collectors.toList());
	}

}
