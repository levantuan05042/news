package com.example.company_news.model.dto;

import lombok.Data;

@Data
public class UsersResponse {
    private String id;
    private String email;
    private String password;
    private String fullname;
    private Integer role;
}
