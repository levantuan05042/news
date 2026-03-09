package com.example.company_news.model.dto.answer;

import lombok.Data;

@Data
public class AnswerRequest {
    private String id;
    private String content;
    private String questionId;
    private String userId;
}