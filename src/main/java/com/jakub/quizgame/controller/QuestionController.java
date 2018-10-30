package com.jakub.quizgame.controller;

import com.amazonaws.Response;
import com.jakub.quizgame.dto.QuestionDTO;
import com.jakub.quizgame.services.QuestionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
