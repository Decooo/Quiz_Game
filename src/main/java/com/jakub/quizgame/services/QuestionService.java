package com.jakub.quizgame.services;

import com.jakub.quizgame.dto.QuestionDTO;
import com.jakub.quizgame.repository.QuestionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class QuestionService {

	private QuestionRepository questionRepository;

	public QuestionService(QuestionRepository questionRepository) {
		this.questionRepository = questionRepository;
	}

	public Collection<QuestionDTO> getQuestions() {
		return questionRepository.findAll()
				.stream()
				.map(q -> new ModelMapper().map(q, QuestionDTO.class))
				.collect(Collectors.toList());
	}
}
