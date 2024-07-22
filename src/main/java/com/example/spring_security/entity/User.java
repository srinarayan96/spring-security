package com.example.spring_security.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    @Column(unique = true)
    private String name;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @Transient
    private List<String> roles;
}
