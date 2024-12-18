package com.payment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.payment.beans.CommonResponseBean;
import com.payment.beans.TransactionBean;
import com.payment.beans.UserBean;
import com.payment.service.UserService;

@RestController
@RequestMapping("/api/payment")
public class HomeController {

	@Autowired
	private UserService userService;
	
	
	@PostMapping("/save-user")
	public ResponseEntity<CommonResponseBean> saveUser(@RequestBody UserBean bean) {
		CommonResponseBean response = userService.saveUser(bean);
		if (response.getStatus()) {
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
		}
	}
	
	@PostMapping("/save-transaction")
	public ResponseEntity<CommonResponseBean> saveTransaction(@RequestBody TransactionBean bean) {
		CommonResponseBean response = userService.createTransaction(bean);
		if (response.getStatus()) {
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
		}
	}
	
	@GetMapping("/getSuccessAmountByUser")
	public ResponseEntity<CommonResponseBean> getSuccessAmount(@RequestParam Long userId) {
		CommonResponseBean response = userService.getSuccessAmountByUser(userId);
		if (response.getStatus()) {
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
		}
	}
	
	@GetMapping("/delete-fail-tansaction")
	public ResponseEntity<CommonResponseBean> deleteFailTransaction() {
		CommonResponseBean response = userService.deleteAllFailTransaction();
		if (response.getStatus()) {
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
		}
	}   
	
	@GetMapping("/get-UserId-With-MaxRefundAmount")
	public ResponseEntity<CommonResponseBean> getUserIdWithMaxRefundAmount() {
		CommonResponseBean response = userService.getUserIdWithMaxRefundAmount();
		if (response.getStatus()) {
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
		}
	} 
}
