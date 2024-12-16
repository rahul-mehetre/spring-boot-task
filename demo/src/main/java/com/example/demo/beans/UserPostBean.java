package com.example.demo.beans;

import lombok.Data;

@Data
public class UserPostBean {

	private Long postId;
	
	private String content;
	
	private String time;
	
	private Long userId;
	
	private Long like;
	
}
