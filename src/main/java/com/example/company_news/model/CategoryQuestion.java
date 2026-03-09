package com.example.company_news.model;

import jakarta.persistence.Id;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "category_question")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryQuestion {
    @Id
    private String id;

    private String name;

    @OneToMany(mappedBy = "category")
    private List<Question> questions;
}
