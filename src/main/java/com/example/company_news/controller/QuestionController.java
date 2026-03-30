package com.example.company_news.controller;

import com.example.company_news.jwt.JwtService;
import com.example.company_news.model.dto.question.QuestionRequest;
import com.example.company_news.model.dto.question.QuestionResponse;
import com.example.company_news.repository.jdbc.QuestionRepositoryJdbc;
import com.example.company_news.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/questions")
@CrossOrigin("*")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;
    private final QuestionRepositoryJdbc questionRepository;
    private final JwtService jwtService;

    @GetMapping
    public List<QuestionResponse> getQuestions(@RequestParam(defaultValue = "newest") String sort) {
        return questionService.getQuestions(sort);
    }

    @PostMapping
    public QuestionResponse create(@RequestBody QuestionRequest req,
                                   @RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        String userId = jwtService.getUserIdFromToken(token);
        req.setUserId(userId);
        return questionService.createQuestion(req);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<?> deleteQuestion(@PathVariable String id,
                                            @RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        String userId = jwtService.getUserIdFromToken(token);
        try {
            questionService.deleteQuestion(id, userId);
            return ResponseEntity.ok().body(Map.of("message", "Xóa thành công"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", e.getMessage()));
        }
    }

    @PostMapping("/update/{id}")
    public QuestionResponse updateQuestion(@PathVariable String id,
                                           @RequestBody QuestionRequest request,
                                           @RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        String userId = jwtService.getUserIdFromToken(token);
        request.setUserId(userId);
        return questionService.updateQuestion(id, request);
    }

    @PostMapping("/search")
    public List<QuestionResponse> search(@RequestBody QuestionRequest request) {
        return questionRepository.search(request);
    }

    @GetMapping("/user/{userId}")
    public List<QuestionResponse> getByUser(@PathVariable String userId) {
        return questionService.getQuestionByUser(userId);
    }
}