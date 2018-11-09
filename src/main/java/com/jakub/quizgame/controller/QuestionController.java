package com.jakub.quizgame.controller;

import com.amazonaws.Response;
import com.jakub.quizgame.dto.QuestionDTO;
import com.jakub.quizgame.exceptions.NotFoundException;
import com.jakub.quizgame.services.QuestionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(value = "/questions")
public class QuestionController {

	private QuestionService questionService;

	public QuestionController(QuestionService questionService) {
		this.questionService = questionService;
	}

	@GetMapping(value = "/get")
	public ResponseEntity<Collection<QuestionDTO>> getQuestions(){
		return new ResponseEntity<>(questionService.getQuestions(), HttpStatus.OK);
	}

	@PostMapping(value = "/post")
	public ResponseEntity<QuestionDTO> addQuestion(@RequestBody QuestionDTO questionDTO) throws NotFoundException {
		return new ResponseEntity<>(questionService.addQuestion(questionDTO),HttpStatus.CREATED);
	}
}
