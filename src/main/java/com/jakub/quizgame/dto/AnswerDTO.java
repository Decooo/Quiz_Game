package com.jakub.quizgame.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.Size;

public class AnswerDTO {

	@JsonIgnore
	private int idAnswer;
	@Size(min = 1, max = 500)
	private String content;
	private Boolean correct;

	public int getIdAnswer() {
		return idAnswer;
	}

	public void setIdAnswer(int idAnswer) {
		this.idAnswer = idAnswer;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Boolean getCorrect() {
		return correct;
	}

	public void setCorrect(Boolean correct) {
		this.correct = correct;
	}
}
