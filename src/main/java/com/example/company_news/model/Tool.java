package com.example.company_news.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tools")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Tool {
    @Id
    private String id;

    private String name;
    private String link;
    private String downloadLink;
    private String image;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}

