package com.example.company_news.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name = "category")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    private String id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "tool_group_id")
    private ToolGroup toolGroup;

    @OneToMany(mappedBy = "category")
    @JsonIgnore
    private List<Tool> tools;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}