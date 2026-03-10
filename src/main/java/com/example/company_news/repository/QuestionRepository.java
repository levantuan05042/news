package com.example.company_news.repository;

import com.example.company_news.model.Question;
import com.example.company_news.model.dto.question.QuestionResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, String> {
    // Mới nhất
    @Query("""
                SELECT new com.example.company_news.model.dto.question.QuestionResponse(
                    q.id,
                    q.title,
                    q.content,
                    q.createdAt,
                    q.user.id,
                    q.user.fullname,
                    q.user.image,
                    COUNT(a.id),
                    q.category.id
                )
                FROM Question q
                LEFT JOIN q.answers a
                GROUP BY q.id, q.title, q.content, q.createdAt,
                         q.user.id, q.user.fullname, q.user.image, q.category.id
                ORDER BY q.createdAt DESC
            """)
    List<QuestionResponse> findNewest();


    // Cũ nhất
    @Query("""
                SELECT new com.example.company_news.model.dto.question.QuestionResponse(
                    q.id,
                    q.title,
                    q.content,
                    q.createdAt,
                    q.user.id,
                    q.user.fullname,
                    q.user.image,
                    COUNT(a.id),
                    q.category.id
                )
                FROM Question q
                LEFT JOIN q.answers a
                GROUP BY q.id, q.title, q.content, q.createdAt,
                         q.user.id, q.user.fullname, q.user.image, q.category.id
                ORDER BY q.createdAt ASC
            """)
    List<QuestionResponse> findOldest();


    // Hot (nhiều câu trả lời nhất)
    @Query("""
                SELECT new com.example.company_news.model.dto.question.QuestionResponse(
                    q.id,
                    q.title,
                    q.content,
                    q.createdAt,
                    q.user.id,
                    q.user.fullname,
                    q.user.image,
                    COUNT(a.id),
                    q.category.id
                )
                FROM Question q
                LEFT JOIN q.answers a
                GROUP BY q.id, q.title, q.content, q.createdAt,
                         q.user.id, q.user.fullname, q.user.image, q.category.id
                ORDER BY COUNT(a.id) DESC
            """)
    List<QuestionResponse> findHot();


    @Query("""
                SELECT new com.example.company_news.model.dto.question.QuestionResponse(
                    q.id,
                    q.title,
                    q.content,
                    q.createdAt,
                    q.user.id,
                    q.user.fullname,
                    q.user.image,
                    COUNT(a.id),
                    q.category.id
                )
                FROM Question q
                LEFT JOIN q.answers a
                WHERE q.user.id = :userId
                GROUP BY q.id, q.title, q.content, q.createdAt,
                         q.user.id, q.user.fullname, q.user.image, q.category.id
                ORDER BY q.createdAt DESC
            """)
    List<QuestionResponse> findByUserId(@Param("userId") String userId);
}