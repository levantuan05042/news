package com.example.company_news.service;

import com.example.company_news.model.Answer;
import com.example.company_news.model.Question;
import com.example.company_news.model.User;
import com.example.company_news.model.dto.answer.AnswerRequest;
import com.example.company_news.model.dto.answer.AnswerResponse;
import com.example.company_news.repository.AnswerRepository;
import com.example.company_news.repository.QuestionRepository;
import com.example.company_news.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AnswerService extends BaseService {

    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;

    public List<AnswerResponse> getAnswersByQuestion(String questionId) {
        return answerRepository
                .findByQuestionIdOrderByCreatedAtDesc(questionId);
    }

    public AnswerResponse createAnswer(AnswerRequest req) {
        Question question = questionRepository
                .findById(req.getQuestionId())
                .orElseThrow(() -> new RuntimeException("Question not found"));
        User user = userRepository
                .findById(req.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Answer answer = new Answer();
        answer.setId(UUID.randomUUID().toString());
        answer.setContent(req.getContent());
        answer.setCreatedAt(LocalDateTime.now());
        answer.setQuestion(question);
        answer.setUser(user);
        Answer saved = answerRepository.save(answer);
        return dto(saved, AnswerResponse.class);
    }

    public void deleteAnswer(String id, String userId) {
        Answer answer = answerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy câu trả lời"));
        if (!answer.getUser().getId().equals(userId)) {
            throw new RuntimeException("Bạn không có quyền xóa câu trả lời này");
        }
        if (answer.getCreatedAt().plusHours(24).isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Chỉ được xóa trong vòng 24h");
        }
        answerRepository.delete(answer);
    }

    public AnswerResponse updateAnswer(String id, AnswerRequest request) {
        Answer answer = answerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy câu trả lời"));
        if (!answer.getUser().getId().equals(request.getUserId())) {
            throw new RuntimeException("Bạn không có quyền sửa câu trả lời này");
        }
        if (answer.getCreatedAt().plusHours(24).isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Chỉ được sửa trong vòng 24h");
        }
        answer.setContent(request.getContent());
        Answer saved = answerRepository.save(answer);
        return dto(saved, AnswerResponse.class);
    }
}