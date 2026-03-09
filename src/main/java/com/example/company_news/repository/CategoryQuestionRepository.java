package com.example.company_news.repository;

import com.example.company_news.model.CategoryQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryQuestionRepository extends JpaRepository<CategoryQuestion, String> {
}
