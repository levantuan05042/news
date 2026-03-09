package com.example.company_news.repository;

import com.example.company_news.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface CategoryRepository extends JpaRepository<Category, String> {
    List<Category> findByToolGroupId(String toolGroupId);
}