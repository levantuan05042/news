//package com.example.company_news.repository.jdbc;
//
//import com.google.common.base.CaseFormat;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.dao.EmptyResultDataAccessException;
//import org.springframework.jdbc.core.BeanPropertyRowMapper;
//import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
//import org.springframework.jdbc.core.namedparam.SqlParameterSource;
//import org.springframework.stereotype.Repository;
//import org.springframework.util.CollectionUtils;
//import com.example.company_news.model.dto.PageSearchRequest;
//
//import java.util.*;
//
//@Repository
//public class BaseRepositoryJdbc {
//
//    @Autowired
//    protected NamedParameterJdbcTemplate jdbc;
//
//    // ================= PAGINATION =================
//
//    protected Map<String, Object> initPageableCriteria(PageSearchRequest request) {
//        Map<String, Object> criteria = new HashMap<>();
//
//        int page = request.getPage() == null ? 0 : request.getPage();
//        int size = request.getSize() == null ? 10 : request.getSize();
//
//        criteria.put("size", size);
//        criteria.put("offset", page * size);
//
//        return criteria;
//    }
//
//    // ================= WHERE =================
//
//    protected String criteriaBuilder(List<String> criteria) {
//        return CollectionUtils.isEmpty(criteria) ? "" : "WHERE " + String.join(" AND ", criteria);
//    }
//
//    // ================= ORDER =================
//
//    protected String orderBuilder(List<String> sorts) {
//
//        if (sorts == null || sorts.isEmpty()) {
//            return "";
//        }
//
//        List<String> orderList = new ArrayList<>();
//
//        for (String str : sorts) {
//
//            String[] arr = str.trim().split(":");
//
//            String column = CaseFormat.LOWER_CAMEL.to(
//                    CaseFormat.LOWER_UNDERSCORE,
//                    arr[0]
//            );
//
//            String direction = arr.length > 1 ? arr[1].toUpperCase() : "ASC";
//
//            orderList.add(column + " " + direction);
//        }
//
//        return "ORDER BY " + String.join(", ", orderList);
//    }
//
//    // ================= QUERY =================
//
//    protected <T> List<T> findList(String query, Map<String, Object> params, Class<T> clazz) {
//        return jdbc.query(query, params, new BeanPropertyRowMapper<>(clazz));
//    }
//
//    protected <T> Optional<T> findOne(String query, Map<String, Object> params, Class<T> clazz) {
//        try {
//            T result = jdbc.queryForObject(query, params, new BeanPropertyRowMapper<>(clazz));
//            return Optional.ofNullable(result);
//        } catch (EmptyResultDataAccessException e) {
//            return Optional.empty();
//        }
//    }
//
//    // ================= UPDATE =================
//
//    protected int executeUpdate(String sql, Map<String, Object> params) {
//        return jdbc.update(sql, params);
//    }
//
//    protected boolean executeNonQuery(String sql, Map<String, Object> params) {
//        return jdbc.update(sql, params) > 0;
//    }
//
//    protected boolean executeNonQuery(String sql, SqlParameterSource params) {
//        return jdbc.update(sql, params) > 0;
//    }
//}