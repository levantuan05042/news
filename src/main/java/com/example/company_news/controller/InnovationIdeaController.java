package com.example.company_news.controller;

import com.example.company_news.jwt.JwtService;
import com.example.company_news.model.dto.categoryinnovationidea.CategoryWithIdeasDTO;
import com.example.company_news.model.dto.innovationidea.InnovationIdeaDTO;
import com.example.company_news.model.dto.innovationidea.InnovationIdeaDetailDTO;
import com.example.company_news.service.InnovationIdeaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/ideas")
@CrossOrigin("*")
@RequiredArgsConstructor
public class InnovationIdeaController {

    private final InnovationIdeaService ideaService;
    private final JwtService jwtService;

    @GetMapping("/all-by-category")
    public ResponseEntity<List<CategoryWithIdeasDTO>> getAllWithIdeas() {
        return ResponseEntity.ok(ideaService.getAllIdeasGroupedByCategory());
    }

    @GetMapping("/category/{categoryId}")
    public List<InnovationIdeaDTO> getIdeasByCategory(@PathVariable String categoryId) {
        return ideaService.getIdeasByCategory(categoryId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InnovationIdeaDetailDTO> getIdeaDetail(@PathVariable String id) {
        return ResponseEntity.ok(ideaService.getDetailDto(id));
    }
}