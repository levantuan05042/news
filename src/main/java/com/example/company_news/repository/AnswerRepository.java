package com.example.company_news.repository;

import com.example.company_news.model.Answer;
import com.example.company_news.model.dto.answer.AnswerResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, String> {

    @Query("""
                 SELECT new com.example.company_news.model.dto.answer.AnswerResponse(
                     a.id,
                     a.content,
                     a.createdAt,
                     a.question.id,
                     a.user.id,
                     a.user.fullname,
                     a.user.image
                 )
                 FROM Answer a
                 WHERE a.question.id = :questionId
                 ORDER BY a.createdAt DESC
            """)
    List<AnswerResponse> findByQuestionIdOrderByCreatedAtDesc(
            @Param("questionId") String questionId
    );

}