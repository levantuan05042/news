package com.example.company_news.service;

import com.example.company_news.model.dto.category.CategoryResponse;
import com.example.company_news.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService extends BaseService {

    private final CategoryRepository repository;

    public List<CategoryResponse> getAll() {

        return repository.findAll()
                .stream()
                .map(category -> {
                    CategoryResponse res = dto(category, CategoryResponse.class);
                    res.setToolGroup(category.getToolGroup().getName());
                    return res;
                })
                .toList();
    }

    public List<CategoryResponse> getByGroup(String groupId) {

        return repository.findByToolGroupId(groupId)
                .stream()
                .map(category -> {
                    CategoryResponse res = dto(category, CategoryResponse.class);
                    res.setToolGroup(category.getToolGroup().getName());
                    return res;
                })
                .toList();
    }

}
