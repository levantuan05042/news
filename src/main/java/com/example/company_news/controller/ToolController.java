package com.example.company_news.controller;


import com.example.company_news.model.dto.category.CategoryWithToolsResponse;
import com.example.company_news.model.dto.tool.ToolRequest;
import com.example.company_news.model.dto.tool.ToolResponse;
import com.example.company_news.repository.jdbc.ToolRepositoryJdbc;
import com.example.company_news.service.ToolService;
import org.springframework.core.io.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/tools")
@CrossOrigin(
        origins = "*",
        exposedHeaders = "Content-Disposition",
        allowedHeaders = "*"
)
@RequiredArgsConstructor
public class ToolController {

    private final ToolService toolService;
    private final ToolRepositoryJdbc toolRepository;

    @GetMapping
    public List<ToolResponse> getAll() {
        return toolService.getAll();
    }

    @PostMapping("/category/{id}")
    public List<ToolResponse> getByCategory(@PathVariable String id) {
        return toolService.getByCategory(id);
    }

    @PostMapping("/search")
    public List<ToolResponse> search(@RequestBody ToolRequest request) {
        return toolRepository.search(request);
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<Resource> download(@PathVariable String id) throws IOException {
        return toolService.download(id);
    }

    @GetMapping("/group/{groupId}/full")
    public List<CategoryWithToolsResponse> getFull(@PathVariable String groupId) {
        return toolService.getByGroupFull(groupId);
    }
    @GetMapping("/all-full")
    public List<CategoryWithToolsResponse> getAllFull() {
        return toolService.getAllFull();
    }
}