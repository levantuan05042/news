package com.example.company_news.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageSearchResponse {
    @JsonIgnore
    private Long total;
}
