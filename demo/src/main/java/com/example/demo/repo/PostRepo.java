package com.example.demo.repo;

import com.example.demo.entities.UserPost;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepo extends JpaRepository<UserPost,Long> {
	
	
}


