package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String name;

    private int age;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
    @JsonManagedReference
//    @JoinColumn(name = "user_id") // foreign key column in the UserPost table
    private List<UserPost> posts;
    
    
    @OneToMany(cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Notification> notifications ;
    
}
