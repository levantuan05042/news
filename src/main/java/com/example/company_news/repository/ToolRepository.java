package com.example.company_news.repository;


import com.example.company_news.model.Tool;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ToolRepository extends JpaRepository<Tool, String> {
    List<Tool> findByCategoryId(String categoryId);
    Page<Tool> findByCategoryId(String categoryId, Pageable pageable);
}