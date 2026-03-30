package com.example.company_news.repository;

import com.example.company_news.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, String> {
    @Query("SELECT c FROM Category c LEFT JOIN FETCH c.toolGroup WHERE c.toolGroup.id = :toolGroupId")
    List<Category> findByToolGroupId(@Param("toolGroupId") String toolGroupId);
}