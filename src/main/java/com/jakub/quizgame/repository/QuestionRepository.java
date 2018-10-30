package com.jakub.quizgame.repository;

import com.jakub.quizgame.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
}
