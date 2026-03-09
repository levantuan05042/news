package com.example.company_news.model.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PageSearchRequest {
    @Min(0)
    private int page = 0;
    @Min(1)
    @Max(100)
    private int size = 10;
    private List<String> sorts = new ArrayList<>();
}
