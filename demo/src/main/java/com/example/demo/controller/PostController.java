package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.beans.CommonResponseBean;
import com.example.demo.beans.UserPostBean;
import com.example.demo.service.PostService;

@RestController
@RequestMapping("/api/post")
public class PostController {
	
	@Autowired
	private PostService postService;
	

	@PostMapping("/create/{userId}")
	public ResponseEntity<CommonResponseBean> createPost(@RequestBody UserPostBean userPostBean,
			@PathVariable Long userId
			){
			userPostBean.setUserId(userId);
			CommonResponseBean response  =  postService.savePost(userPostBean);
			if(response.getStatus()) {
				return ResponseEntity.status(HttpStatus.CREATED).body(response);
			}else {
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
			}
	}
	
	@PutMapping("/like/{userId}/{postId}")
	public ResponseEntity<CommonResponseBean> insertLike(@RequestBody UserPostBean userPostBean,
			@PathVariable Long userId,@PathVariable Long postId
			){
			userPostBean.setUserId(userId);
			CommonResponseBean response  =  postService.insertLike(userPostBean,userId,postId);
			if(response.getStatus()) {
				return ResponseEntity.status(HttpStatus.CREATED).body(response);
			}else {
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
			}
	}
}
