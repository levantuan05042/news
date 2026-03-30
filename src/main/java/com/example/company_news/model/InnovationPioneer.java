package com.example.company_news.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "innovation_pioneer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InnovationPioneer {
    @Id
    private String id;
    @Column(name = "unit_name")
    private String unitName;
    private String image;
    private String title;
    @Column(columnDefinition = "TEXT")
    private String content;
    private String file;
    private LocalDateTime createdAt;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
