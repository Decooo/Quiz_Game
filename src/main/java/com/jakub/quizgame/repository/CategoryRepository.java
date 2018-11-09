package com.jakub.quizgame.repository;

import com.jakub.quizgame.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
	Optional<Category> findByIdCategory(Integer integer);
}
