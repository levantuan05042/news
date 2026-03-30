package com.example.company_news.model.dto.innovationpioneer;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InnovationPioneerDTO {
    private String id;
    private String title;
    private String image;
    private String content;
    private String unitName;
    private String createdAt;
}