package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.beans.CommonResponseBean;

@RestController
@RequestMapping("/api/post/like")
public class LikeController {

	@PostMapping("/")
	public ResponseEntity<CommonResponseBean> insertLike(){
		
		
		return null;
	}
}
