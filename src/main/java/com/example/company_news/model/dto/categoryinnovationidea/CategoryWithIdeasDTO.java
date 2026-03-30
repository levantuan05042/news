package com.example.company_news.model.dto.categoryinnovationidea;

import com.example.company_news.model.dto.innovationidea.InnovationIdeaDTO;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryWithIdeasDTO {
    private String categoryId;
    private String categoryName;
    private List<InnovationIdeaDTO> ideas;
}