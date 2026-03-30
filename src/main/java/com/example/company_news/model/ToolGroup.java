package com.example.company_news.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name = "tool_group")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ToolGroup {
    @Id
    private String id;

    private String name;

    @OneToMany(mappedBy = "toolGroup")
    @JsonIgnore
    private List<Category> categories;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
