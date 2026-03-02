package com.example.company_news.handler;

import lombok.Getter;

@Getter
public class NotFoundException extends Exception {

    private Object details;

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Object details) {
        super(message);
        this.details = details;
    }

}
