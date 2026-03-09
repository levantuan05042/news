package com.example.company_news.model.dto.category;

import com.example.company_news.model.dto.PageSearchRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryRequest extends PageSearchRequest {

    private String name;
    private String toolGroup;

}