package com.example.company_news.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "innovation_idea")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InnovationIdea {
    @Id
    private String id;
    private String image;
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String fullContent;

    private LocalDateTime createdAt;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryInnovationIdea category;
}
