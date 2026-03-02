package com.example.company_news.repository;


import com.example.company_news.model.Tool;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ToolRepository extends JpaRepository<Tool, String> {
    List<Tool> findByCategoryId(String categoryId);
}
