package com.jakub.quizgame.services;

import com.jakub.quizgame.dto.CategoryDTO;
import com.jakub.quizgame.repository.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class CategoryService {

	private CategoryRepository categoryRepository;

	public CategoryService(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	public Collection<CategoryDTO> getCategories() {
		return categoryRepository.findAll()
				.stream()
				.map(c -> new ModelMapper().map(c, CategoryDTO.class))
				.collect(Collectors.toList());
	}
}
