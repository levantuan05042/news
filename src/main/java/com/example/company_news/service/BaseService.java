package com.example.company_news.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseService {
    @Autowired
    private ModelMapper modelMapper;

    protected <In, Out> Out dto(In input, Class<Out> clazz) {
        return modelMapper.map(input, clazz);
    }
}