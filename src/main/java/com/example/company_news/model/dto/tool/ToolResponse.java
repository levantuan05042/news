package com.example.company_news.model.dto.tool;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ToolResponse {
    private String id;
    private String name;
    private String link;
    private String downloadLink;
    private String image;
    private String category;
    @JsonIgnore
    private String categoryId;
}
