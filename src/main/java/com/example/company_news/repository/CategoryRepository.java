package com.example.company_news.repository;

import com.example.company_news.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface CategoryRepository extends JpaRepository<Category, String> {

    @Query("""
        SELECT new map(
            c.id as id, 
            c.name as name, 
            COUNT(t.id) as total)
        FROM Category c
        LEFT JOIN c.tools t
        GROUP BY c.id
    """)
    List<Map<String, Object>> getCategoriesWithCount();
}