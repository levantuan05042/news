package com.example.company_news.controller;

import com.example.company_news.model.ToolGroup;
import com.example.company_news.model.dto.toolgroup.ToolGroupResponse;
import com.example.company_news.repository.ToolGroupRepository;
import com.example.company_news.service.ToolGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/groups")
@CrossOrigin("*")
@RequiredArgsConstructor
public class ToolGroupController {

    private final ToolGroupService toolGroupService;

    @GetMapping
    public List<ToolGroupResponse> getAll() {
        return toolGroupService.getAll();
    }

}