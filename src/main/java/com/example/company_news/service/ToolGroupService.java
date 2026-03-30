package com.example.company_news.service;

import com.example.company_news.model.dto.toolgroup.ToolGroupResponse;
import com.example.company_news.repository.ToolGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;



@Service
@RequiredArgsConstructor
public class ToolGroupService extends BaseService {
    private final ToolGroupRepository repository;

    public List<ToolGroupResponse> getAll() {
        return repository.findAll()
                .stream()
                .map(group -> dto(group, ToolGroupResponse.class))
                .toList();
    }
}