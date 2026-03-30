package com.example.company_news.service;

import com.example.company_news.model.CategoryQuestion;
import com.example.company_news.model.Question;
import com.example.company_news.model.User;
import com.example.company_news.model.dto.question.QuestionRequest;
import com.example.company_news.model.dto.question.QuestionResponse;
import com.example.company_news.repository.CategoryQuestionRepository;
import com.example.company_news.repository.QuestionRepository;
import com.example.company_news.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class QuestionService extends BaseService {

    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;
    private final CategoryQuestionRepository categoryQuestionRepository;

    public QuestionResponse createQuestion(QuestionRequest req) {
        User user = userRepository.findById(req.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
        Question question = new Question();
        question.setId(UUID.randomUUID().toString());
        question.setTitle(req.getTitle());
        question.setContent(req.getContent());
        question.setUser(user);
        question.setCreatedAt(LocalDateTime.now());
        if (req.getCategoryId() != null) {
            CategoryQuestion category = categoryQuestionRepository
                    .findById(req.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));

            question.setCategory(category);
        }
        Question saved = questionRepository.save(question);
        return dto(saved, QuestionResponse.class);
    }

    public void deleteQuestion(String id, String userId) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Question not found"));
        if(!question.getUser().getId().equals(userId)) {
            throw new RuntimeException("Bạn không có quyền xóa câu hỏi này");
        }
        questionRepository.delete(question);
    }

    public QuestionResponse updateQuestion(String id, QuestionRequest req) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Question not found"));

        if (!question.getUser().getId().equals(req.getUserId())) {
            throw new RuntimeException("Bạn không có quyền sửa câu hỏi này");
        }
        question.setTitle(req.getTitle());
        question.setContent(req.getContent());
        if (req.getCategoryId() != null) {

            CategoryQuestion category = categoryQuestionRepository
                    .findById(req.getCategoryId()).orElseThrow(() -> new RuntimeException("Category not found"));

            question.setCategory(category);
        } else {
            question.setCategory(null);
        }
        Question updated = questionRepository.save(question);
        return dto(updated, QuestionResponse.class);
    }

    public List<QuestionResponse> getQuestions(String sort) {
        switch (sort) {
            case "oldest":
                return questionRepository.findOldest();
            case "hot":
                return questionRepository.findHot();
            default:
                return questionRepository.findNewest();
        }
    }

    public List<QuestionResponse> getQuestionByUser(String userId) {
        return questionRepository.findByUserId(userId);
    }

}