package com.example.company_news.model.dto.tool;

import com.example.company_news.model.Category;
import com.example.company_news.model.dto.PageSearchRequest;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ToolRequest extends PageSearchRequest {
    private String name;
    private String category;
}
