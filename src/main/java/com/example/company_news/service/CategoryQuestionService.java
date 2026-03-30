package com.example.company_news.service;

import com.example.company_news.model.dto.categoryquestion.CategoryQuestionResponse;
import com.example.company_news.model.dto.toolgroup.ToolGroupResponse;
import com.example.company_news.repository.CategoryQuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryQuestionService extends BaseService {
    private final CategoryQuestionRepository categoryQuestionRepository;

    public List<CategoryQuestionResponse> getAll() {
        return categoryQuestionRepository.findAll()
                .stream()
                .map(categoryquestion -> dto(categoryquestion, CategoryQuestionResponse.class))
                .toList();
    }
}
