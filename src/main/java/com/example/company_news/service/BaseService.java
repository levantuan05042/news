package com.example.company_news.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

@Service


public class BaseService {
    @Autowired
    private ModelMapper modelMapper;

    protected <In, Out> Out dto(In input, Class<Out> clazz) {
        return modelMapper.map(input, clazz);
    }
}
