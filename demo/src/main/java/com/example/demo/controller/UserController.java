package com.example.demo.controller;

import com.example.demo.beans.CommonResponseBean;
import com.example.demo.beans.UserBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.example.demo.service.UserService;

@Controller
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/saveUser")
	public ResponseEntity<CommonResponseBean> saveUser(@RequestBody UserBean bean) {
		System.out.println(bean+"Bean");
		CommonResponseBean response = userService.saveUser(bean);
		if(response.getStatus()) {
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		}else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
		}
	}
	
	@GetMapping("/getUser/{userId}")
	public ResponseEntity<CommonResponseBean> getUser(@PathVariable Long userId) {
		CommonResponseBean response = userService.getUser(userId);
		if(response.getStatus()) {
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		}else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
		}
	}
	
	@GetMapping("/getUserNotificationCount/{userId}")
	public ResponseEntity<CommonResponseBean> getUserNotificationCount(@PathVariable Long userId) {
		CommonResponseBean response = userService.getUserNotificationCount(userId);
		if(response.getStatus()) {
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		}else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
		}
	}
}
