package com.example.company_news.model.dto.category;

import com.example.company_news.model.dto.tool.ToolResponse;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryWithToolsResponse {
    private String categoryId;
    private String categoryName;
    private List<ToolResponse> tools;
}
