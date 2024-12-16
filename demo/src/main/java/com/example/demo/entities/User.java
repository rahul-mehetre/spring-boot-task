package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String name;

    private int age;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id") // foreign key column in the UserPost table
    private List<UserPost> posts;
    
    
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id") // foreign key column in the UserPost table
    private List<Notification> notifications ;
    
}
