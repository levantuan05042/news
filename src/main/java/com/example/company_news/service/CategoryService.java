package com.example.company_news.service;

import com.example.company_news.model.Category;
import com.example.company_news.model.ToolGroup;
import com.example.company_news.model.dto.category.CategoryResponse;
import com.example.company_news.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryService extends BaseService {
    private final CategoryRepository repository;

    public List<CategoryResponse> getAll() {
        return repository.findAll().stream()
                .map(this::mapToResponse)
                .toList();
    }

    public List<CategoryResponse> getByGroup(String groupId) {
        return repository.findByToolGroupId(groupId).stream()
                .map(this::mapToResponse)
                .toList();
    }

    private CategoryResponse mapToResponse(Category cat) {
        CategoryResponse res = dto(cat, CategoryResponse.class);
        res.setToolGroup(Optional.ofNullable(cat.getToolGroup())
                .map(ToolGroup::getName)
                .orElse("Không xác định"));
        return res;
    }
}
