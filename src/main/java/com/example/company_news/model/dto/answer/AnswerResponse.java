package com.example.company_news.model.dto.answer;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnswerResponse {
    private String id;
    private String content;
    private LocalDateTime createdAt;
    private String questionId;
    private String userId;
    private String fullname;
    private String image;
}