package com.example.company_news.controller;


import com.example.company_news.model.Tool;
import com.example.company_news.repository.ToolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tools")
@CrossOrigin("*")
@RequiredArgsConstructor
public class ToolController {

    private final ToolRepository repository;

    @GetMapping
    public List<Tool> getAll() {
        return repository.findAll();
    }

    @GetMapping("/category/{id}")
    public List<Tool> getByCategory(@PathVariable String id) {
        return repository.findByCategoryId(id);
    }
}