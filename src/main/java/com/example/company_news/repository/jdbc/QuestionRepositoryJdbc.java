package com.example.company_news.repository.jdbc;

import com.example.company_news.model.dto.question.QuestionRequest;
import com.example.company_news.model.dto.question.QuestionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class QuestionRepositoryJdbc {
    @Autowired
    private NamedParameterJdbcTemplate jdbc;

    public List<QuestionResponse> search(QuestionRequest request) {

        if (request == null) {
            request = new QuestionRequest();
        }

        List<String> conditions = new ArrayList<>();
        Map<String, Object> params = new HashMap<>();
        if (request.getKeyword() != null && !request.getKeyword().isBlank()) {
            conditions.add("""
            (
            LOWER(q.title) LIKE LOWER(:keyword)
            OR LOWER(q.content) LIKE LOWER(:keyword)
            )
        """);

            params.put("keyword", "%" + request.getKeyword() + "%");
        }

        // FILTER CATEGORY
        if (request.getCategoryId() != null && !request.getCategoryId().isBlank()) {

            conditions.add("q.category_id = :categoryId");
            params.put("categoryId", request.getCategoryId());
        }

        String where = conditions.isEmpty()
                ? ""
                : "WHERE " + String.join(" AND ", conditions);

        String sql = """
        SELECT
            q.id,
            q.title,
            q.content,
            q.created_at AS createdAt,
            u.id AS userId,
            u.fullname,
            u.image,
            COUNT(a.id) AS answerCount,
            q.category_id AS categoryId
        FROM questions q
        LEFT JOIN users u ON q.user_id = u.id
        LEFT JOIN answers a ON a.question_id = q.id
        """ + where + """
        GROUP BY q.id, u.id
        ORDER BY q.created_at DESC
        """;

        return jdbc.query(
                sql,
                params,
                new BeanPropertyRowMapper<>(QuestionResponse.class)
        );
    }
}
