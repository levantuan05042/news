package com.example.company_news.controller;

import com.example.company_news.model.dto.category.CategoryResponse;
import com.example.company_news.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin("*")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public List<CategoryResponse> getAll() {
        return categoryService.getAll();
    }

    @GetMapping("/group/{groupId}")
    public List<CategoryResponse> getByGroup(@PathVariable String groupId) {
        return categoryService.getByGroup(groupId);
    }
}
