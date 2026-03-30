package com.example.company_news.repository;


import com.example.company_news.model.Tool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ToolRepository extends JpaRepository<Tool, String>, JpaSpecificationExecutor<Tool> {
    List<Tool> findByCategoryId(String categoryId);
    @Query("SELECT t FROM Tool t LEFT JOIN FETCH t.category")
    List<Tool> findAllWithCategory();

    @Query("SELECT t FROM Tool t LEFT JOIN FETCH t.category WHERE t.category.id = :id")
    List<Tool> findByCategoryIdWithCategory(@Param("id") String id);

    @Query("SELECT t FROM Tool t LEFT JOIN FETCH t.category WHERE t.category.id IN :ids")
    List<Tool> findByCategoryIdsWithCategory(@Param("ids") List<String> ids);
}