package com.example.company_news.model.dto.question;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionResponse {

    private String id;
    private String title;
    private String content;
    private LocalDateTime createdAt;

    private String userId;
    private String fullname;
    private String image;

    private Long answerCount;
    private String categoryId;
}