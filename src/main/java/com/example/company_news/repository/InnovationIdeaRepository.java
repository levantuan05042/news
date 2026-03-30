package com.example.company_news.repository;

import com.example.company_news.model.InnovationIdea;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InnovationIdeaRepository extends JpaRepository<InnovationIdea, String> {

    @EntityGraph(attributePaths = {"category", "user"})
    List<InnovationIdea> findByCategoryId(String categoryId);

    @EntityGraph(attributePaths = {"category", "user"})
    Optional<InnovationIdea> findById(String id);
}
