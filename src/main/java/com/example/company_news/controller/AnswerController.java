package com.example.company_news.controller;

import com.example.company_news.jwt.JwtService;
import com.example.company_news.model.dto.answer.AnswerRequest;
import com.example.company_news.model.dto.answer.AnswerResponse;

import com.example.company_news.service.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/answers")
@CrossOrigin("*")
@RequiredArgsConstructor
public class AnswerController {

    private final AnswerService answerService;
    private final JwtService jwtService;

    @GetMapping("/{questionId}")
    public List<AnswerResponse> getAnswers(@PathVariable String questionId) {
        return answerService.getAnswersByQuestion(questionId);
    }

    @PostMapping
    public AnswerResponse createAnswer(@RequestBody AnswerRequest answer,
                                       @RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        String userId = jwtService.getUserIdFromToken(token);
        answer.setUserId(userId);
        return answerService.createAnswer(answer);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<?> deleteAnswer(@PathVariable String id,
                                          @RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        String userId = jwtService.getUserIdFromToken(token); // lấy userId từ token
        answerService.deleteAnswer(id, userId); // truyền userId vào service để check quyền
        return ResponseEntity.ok().build();
    }

    @PostMapping("/update/{id}")
    public AnswerResponse updateAnswer(@PathVariable String id,
                                       @RequestBody AnswerRequest request,
                                       @RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        String userId = jwtService.getUserIdFromToken(token);
        request.setUserId(userId); // gán userId từ token
        return answerService.updateAnswer(id, request);
    }
}