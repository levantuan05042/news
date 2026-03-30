package com.example.company_news.model.dto;

import lombok.Data;

@Data
public class UsersRequest {
    private String userId;
    private String fullName;
    private String password;
    private String image;
    private String currentPassword;
}
