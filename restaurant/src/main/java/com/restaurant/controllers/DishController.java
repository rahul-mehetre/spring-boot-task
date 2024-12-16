package com.restaurant.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restaurant.beans.CommonResponse;
import com.restaurant.beans.DishBean;
import com.restaurant.beans.OrderBean;
import com.restaurant.service.DishServices;
import com.restaurant.service.OrderService;

@RestController
@RequestMapping("/api/restaurant")
public class DishController {

	@Autowired
	private DishServices dishServices;
	
	@Autowired
	private OrderService orderService;

	@PostMapping("/save")
	public ResponseEntity<CommonResponse> addDish(@RequestBody DishBean bean) {
		CommonResponse response = dishServices.saveDish(bean);
		if (response.getStatus()) {
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
		}
	}
	
	@GetMapping("/getMenuCard")
	public ResponseEntity<CommonResponse> getMenuCard() {
		CommonResponse response = dishServices.getMenuCard();
		if (response.getStatus()) {
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
		}
	}
	
	
	@PostMapping("/create-order")
	public ResponseEntity<CommonResponse> createOrder(@RequestBody OrderBean bean) {
		CommonResponse response = orderService.createOrder(bean);
		if (response.getStatus()) {
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
		}
	}
	
	@GetMapping("/getOrderById/{orderId}")
	public ResponseEntity<CommonResponse> getOrderById(@PathVariable Long orderId) {
		CommonResponse response = orderService.getOrder(orderId);
		if (response.getStatus()) {
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
		}
	}
	
	@GetMapping("/getTableBill/{tableNo}")
	public ResponseEntity<CommonResponse> getTableBill(@PathVariable Long tableNo) {
		CommonResponse response = orderService.getBillByTableNo(tableNo);
		if (response.getStatus()) {
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
		}
	}
	
	@PutMapping("/updateOrder/{tableNo}")
	public ResponseEntity<CommonResponse> updateOrder(@PathVariable Long tableNo,@RequestBody OrderBean bean) {
		CommonResponse response = orderService.addDishInOrder(bean,tableNo);
		if (response.getStatus()) {
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
		}
	}
	
	@DeleteMapping("/closeOrder/{tableNo}")
	public ResponseEntity<CommonResponse> closeOrder(@PathVariable Long tableNo) {
		CommonResponse response = orderService.closeOrder(tableNo);
		if (response.getStatus()) {
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
		}
	}
	
}
