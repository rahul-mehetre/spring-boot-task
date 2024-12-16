package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
public class UserPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    private String content;

    private String time;

    
    private Long likesCount;
    
   
    // The inverse relationship can optionally be mapped if needed (e.g., a many-to-one with User)
    
    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "post_id") // foreign key column in the UserPost table
    private List<Notification> notifications ;
}
