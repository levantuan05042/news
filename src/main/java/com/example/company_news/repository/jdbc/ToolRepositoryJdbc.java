package com.example.company_news.repository.jdbc;

import com.example.company_news.model.dto.tool.ToolRequest;
import com.example.company_news.model.dto.tool.ToolResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ToolRepositoryJdbc {

    @Autowired
    private NamedParameterJdbcTemplate jdbc;

    public List<ToolResponse> search(ToolRequest request) {

        if (request == null) {
            request = new ToolRequest();
        }

        List<String> conditions = new ArrayList<>();
        Map<String, Object> params = new HashMap<>();

        if (request.getName() != null && !request.getName().isBlank()) {

            conditions.add("(t.name LIKE :keyword OR c.name LIKE :keyword)");
            params.put("keyword", "%" + request.getName() + "%");

        }

        String where = conditions.isEmpty()
                ? ""
                : "WHERE " + String.join(" AND ", conditions);

        String sql = """
            SELECT
                t.id,
                t.name,
                t.link,
                t.download_link AS downloadLink,
                t.image,
                c.name AS category
            FROM tools t
            LEFT JOIN category c ON t.category_id = c.id
            """ + where;

        return jdbc.query(
                sql,
                params,
                new BeanPropertyRowMapper<>(ToolResponse.class)
        );
    }
}