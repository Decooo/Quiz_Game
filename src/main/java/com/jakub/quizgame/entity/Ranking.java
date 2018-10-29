package com.jakub.quizgame.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "ranking")
public class Ranking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idRanking;

	@Size(min = 1, max = 50)
	private String name;

	private int result;

	public int getIdRanking() {
		return idRanking;
	}

	public void setIdRanking(int idRanking) {
		this.idRanking = idRanking;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}
}
