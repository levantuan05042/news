package com.example.company_news.service;

import com.example.company_news.model.Category;
import com.example.company_news.model.Tool;
import com.example.company_news.model.dto.category.CategoryWithToolsResponse;
import com.example.company_news.model.dto.tool.ToolRequest;
import com.example.company_news.model.dto.tool.ToolResponse;
import com.example.company_news.repository.CategoryRepository;
import com.example.company_news.repository.ToolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.io.IOException;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ToolService extends BaseService {
    private final ToolRepository repository;
    private final CategoryRepository categoryRepository;

    public List<ToolResponse> getAll() {
        return repository.findAllWithCategory().stream()
                .map(this::mapToResponse)
                .toList();
    }

    public List<ToolResponse> getByCategory(String id) {
        return repository.findByCategoryIdWithCategory(id).stream()
                .map(this::mapToResponse)
                .toList();
    }

    public List<CategoryWithToolsResponse> getByGroupFull(String groupId) {
        List<Category> categories = categoryRepository.findByToolGroupId(groupId);
        if (categories.isEmpty()) return List.of();
        List<String> catIds = categories.stream().map(Category::getId).toList();
        List<Tool> allTools = repository.findByCategoryIdsWithCategory(catIds);
        Map<String, List<ToolResponse>> toolsByCatMap = allTools.stream()
                .map(this::mapToResponse)
                .collect(Collectors.groupingBy(res -> res.getCategoryId() != null ? res.getCategoryId() : "other"));
        return categories.stream().map(cat -> new CategoryWithToolsResponse(
                cat.getId(),
                cat.getName(),
                toolsByCatMap.getOrDefault(cat.getId(), List.of())
        )).toList();
    }

    @Value("${app.upload.dir}")
    private String uploadDir;
    public ResponseEntity<Resource> download(String id) throws IOException {
        Tool tool = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tool không tồn tại"));

        Path path = Paths.get(uploadDir).resolve(tool.getDownloadLink()).normalize();

        if (!Files.exists(path) || !Files.isReadable(path)) {
            return ResponseEntity.notFound().build();
        }

        Resource resource = new UrlResource(path.toUri());
        String encodedFilename = URLEncoder.encode(resource.getFilename(), StandardCharsets.UTF_8).replace("+", "%20");

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename*=UTF-8''" + encodedFilename)
                .body(resource);
    }

    private ToolResponse mapToResponse(Tool tool) {
        ToolResponse res = dto(tool, ToolResponse.class);
        if (tool.getCategory() != null) {
            res.setCategory(tool.getCategory().getName());
            res.setCategoryId(tool.getCategory().getId());
        } else {
            res.setCategory("Khác");
            res.setCategoryId("other");
        }
        return res;
    }

    public List<CategoryWithToolsResponse> getAllFull() {
        List<Category> categories = categoryRepository.findAll();
        if (categories.isEmpty()) return List.of();

        List<Tool> allTools = repository.findAllWithCategory();

        Map<String, List<ToolResponse>> toolsByCatMap = allTools.stream()
                .map(this::mapToResponse)
                .collect(Collectors.groupingBy(
                        res -> res.getCategoryId() != null ? res.getCategoryId() : "other"
                ));

        return categories.stream()
                .map(cat -> new CategoryWithToolsResponse(
                        cat.getId(),
                        cat.getName(),
                        toolsByCatMap.getOrDefault(cat.getId(), List.of())
                ))
                .filter(res -> !res.getTools().isEmpty()) // Chỉ trả về những Category có Tool
                .toList();
    }}