package com.example.company_news.repository;

import com.example.company_news.model.InnovationPioneer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InnovationPioneerRepository extends JpaRepository<InnovationPioneer, String> {
    List<InnovationPioneer> findAllByOrderByCreatedAtDesc();
}
