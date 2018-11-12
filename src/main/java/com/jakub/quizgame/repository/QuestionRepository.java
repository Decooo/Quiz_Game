package com.jakub.quizgame.repository;

import com.jakub.quizgame.entity.Category;
import com.jakub.quizgame.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Integer> {

	Optional<Question> findByIdQuestion(Integer integer);

	ArrayList<Question> findByCategory(Category category);
}
