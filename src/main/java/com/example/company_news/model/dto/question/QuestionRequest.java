package com.example.company_news.model.dto.question;

import lombok.Data;

@Data
public class QuestionRequest {

    private String keyword;

    private String title;
    private String content;
    private String userId;
    private String categoryId;
}