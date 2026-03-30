package com.example.company_news.model.dto.innovationpioneer;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InnovationPioneerDetailDTO {
    private String id;
    private String title;
    private String image;
    private String content;      // Nội dung chi tiết (TEXT)
    private String file;
    private String fileType;
    private String createdAt;
    private String unitName;
}
