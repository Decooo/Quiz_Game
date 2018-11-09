package com.jakub.quizgame.dto;

import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

public class QuestionDTO {
	private int id;
	@Size(min = 1, max = 500)
	private String content;
	private int idCategory;
	private Set<AnswerDTO> answers = new HashSet<>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Set<AnswerDTO> getAnswers() {
		return answers;
	}

	public void setAnswers(Set<AnswerDTO> answers) {
		this.answers = answers;
	}

	public int getIdCategory() {
		return idCategory;
	}

	public void setIdCategory(int idCategory) {
		this.idCategory = idCategory;
	}
}
