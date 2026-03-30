package com.example.company_news.model.dto.innovationidea;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InnovationIdeaDTO {
    private String id;
    private String title;
    private String content;
    private String image;
    private LocalDateTime createdAt;
    private String categoryId;
}