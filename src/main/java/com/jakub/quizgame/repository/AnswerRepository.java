package com.jakub.quizgame.repository;

import com.jakub.quizgame.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer,Integer> {
}
