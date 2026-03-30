package com.example.company_news.controller;

import com.example.company_news.model.dto.innovationpioneer.InnovationPioneerDTO;
import com.example.company_news.model.dto.innovationpioneer.InnovationPioneerDetailDTO;
import com.example.company_news.service.InnovationPioneerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pioneers")
@CrossOrigin("*")
@RequiredArgsConstructor
public class InnovationPioneerController {
    private final InnovationPioneerService pioneerService;

    @GetMapping
    public ResponseEntity<List<InnovationPioneerDTO>> getList() {
        return ResponseEntity.ok(pioneerService.getAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<InnovationPioneerDetailDTO> getDetail(@PathVariable String id) {
        return ResponseEntity.ok(pioneerService.getDetail(id));
    }
}