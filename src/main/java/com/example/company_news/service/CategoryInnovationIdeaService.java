package com.example.company_news.service;

import com.example.company_news.model.dto.categoryinnovationidea.CategoryInnovationIdeaDTO;
import com.example.company_news.repository.CategoryInnovationIdeaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryInnovationIdeaService {

    private final CategoryInnovationIdeaRepository categoryRepo;
    public List<CategoryInnovationIdeaDTO> getAllCategories() {
        return categoryRepo.findAll().stream()
                .map(cat -> new CategoryInnovationIdeaDTO(cat.getId(), cat.getName()))
                .collect(Collectors.toList());
    }
}
