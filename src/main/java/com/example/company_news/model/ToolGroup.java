package com.example.company_news.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
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
}
