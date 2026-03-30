package com.example.company_news.controller;

import com.example.company_news.model.dto.categoryinnovationidea.CategoryInnovationIdeaDTO;
import com.example.company_news.service.CategoryInnovationIdeaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/categories_innovation")
@CrossOrigin("*")
@RequiredArgsConstructor
public class CategoryInnovationIdeaController {

    private final CategoryInnovationIdeaService categoryService;

    @GetMapping
    public List<CategoryInnovationIdeaDTO> getAllCategories() {
        return categoryService.getAllCategories();
    }
}