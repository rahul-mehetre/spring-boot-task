package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notificationID;

    private String date;

    

   }
