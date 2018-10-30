package com.jakub.quizgame.repository;

import com.jakub.quizgame.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
