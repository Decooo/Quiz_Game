package com.jakub.quizgame.services;

import com.jakub.quizgame.dto.QuestionDTO;
import com.jakub.quizgame.entity.Answer;
import com.jakub.quizgame.entity.Question;
import com.jakub.quizgame.exceptions.NotFoundException;
import com.jakub.quizgame.repository.AnswerRepository;
import com.jakub.quizgame.repository.CategoryRepository;
import com.jakub.quizgame.repository.QuestionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class QuestionService {

	private QuestionRepository questionRepository;
	private CategoryRepository categoryRepository;
	private AnswerRepository answerRepository;

	public QuestionService(QuestionRepository questionRepository, CategoryRepository categoryRepository, AnswerRepository answerRepository) {
		this.questionRepository = questionRepository;
		this.categoryRepository = categoryRepository;
		this.answerRepository = answerRepository;
	}

	public Collection<QuestionDTO> getQuestions() {
		return questionRepository.findAll()
				.stream()
				.map(q -> new ModelMapper().map(q, QuestionDTO.class))
				.collect(Collectors.toList());
	}

	public QuestionDTO addQuestion(QuestionDTO questionDTO) throws NotFoundException {
		Question question = new Question();
		question.setContent(questionDTO.getContent());
		var category = categoryRepository.findByIdCategory(questionDTO.getIdCategory());
		if (!category.isPresent()) {
			throw new NotFoundException("Podana kategoria nie istnieje");
		}
		question.setCategory(category.get());
		var finalQuestion = questionRepository.save(question);

		var answerList = questionDTO.getAnswers().stream()
				.map(a -> new ModelMapper().map(a, Answer.class))
				.peek(answer -> answer.setQuestion(finalQuestion))
				.collect(Collectors.toList());

		answerRepository.saveAll(answerList);

		return new ModelMapper().map(questionRepository.findByIdQuestion(finalQuestion.getIdQuestion()).get(), QuestionDTO.class);
	}
}
