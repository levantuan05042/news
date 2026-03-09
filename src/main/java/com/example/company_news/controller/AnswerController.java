package com.example.company_news.controller;

import com.example.company_news.model.Answer;
import com.example.company_news.model.dto.answer.AnswerRequest;
import com.example.company_news.model.dto.answer.AnswerResponse;
import com.example.company_news.service.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/answers")
@CrossOrigin("*")
@RequiredArgsConstructor
public class AnswerController {

    private final AnswerService answerService;

    @GetMapping("/{questionId}")
    public List<AnswerResponse> getAnswers(@PathVariable String questionId){
        return answerService.getAnswersByQuestion(questionId);
    }

    @PostMapping
    public AnswerResponse createAnswer(@RequestBody AnswerRequest answer){
        return answerService.createAnswer(answer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAnswer(@PathVariable String id) {
        answerService.deleteAnswer(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public AnswerResponse updateAnswer(@PathVariable String id,
                                       @RequestBody AnswerRequest request){
        return answerService.updateAnswer(id, request);
    }
}