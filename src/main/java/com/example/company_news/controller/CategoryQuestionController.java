package com.example.company_news.controller;

import com.example.company_news.model.dto.categoryquestion.CategoryQuestionResponse;
import com.example.company_news.model.dto.toolgroup.ToolGroupResponse;
import com.example.company_news.service.CategoryQuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/categoryquestion")
@CrossOrigin("*")
@RequiredArgsConstructor
public class CategoryQuestionController {


    private final CategoryQuestionService categoryQuestionService;
    @GetMapping
    public List<CategoryQuestionResponse> getAll() {
        return categoryQuestionService.getAll();
    }

}
