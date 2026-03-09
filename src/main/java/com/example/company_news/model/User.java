package com.example.company_news.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    private String id;
    private String email;
    private String password;
    private String fullname;
    private Integer role;

    private String image;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Question> questions;
}
