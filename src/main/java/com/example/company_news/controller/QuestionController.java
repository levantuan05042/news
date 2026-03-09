package com.example.company_news.controller;

import com.example.company_news.model.Question;
import com.example.company_news.model.dto.question.QuestionRequest;
import com.example.company_news.model.dto.question.QuestionResponse;
import com.example.company_news.repository.jdbc.QuestionRepositoryJdbc;
import com.example.company_news.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/questions")
@CrossOrigin("*")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;
    private final QuestionRepositoryJdbc questionRepository;

    // Lấy tất cả câu hỏi
    @GetMapping
    public List<QuestionResponse> getQuestions(
            @RequestParam(defaultValue = "newest") String sort
    ){
        return questionService.getQuestions(sort);
    }

    // Tạo câu hỏi
    @PostMapping
    public QuestionResponse create(@RequestBody QuestionRequest req){
        return questionService.createQuestion(req);
    }

    // Xóa câu hỏi
    @DeleteMapping("/{id}")
    public void deleteQuestion(@PathVariable String id){
        questionService.deleteQuestion(id);
    }

    @PutMapping("/{id}")
    public QuestionResponse updateQuestion(@PathVariable String id, @RequestBody QuestionRequest request){
        return questionService.updateQuestion(id, request);
    }

    @PostMapping("/search")
    public List<QuestionResponse> search(@RequestBody QuestionRequest request){
//        return toolService.search(request);
        return questionRepository.search(request);
    }

}