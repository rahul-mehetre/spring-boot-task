package com.ircts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ircts.beans.CommonResponseBean;
import com.ircts.beans.PassengerBean;
import com.ircts.service.FoodOrderService;
import com.ircts.service.FoodService;

@RestController
@RequestMapping("/api/irctc/order")
public class OrderController {

	@Autowired
	private FoodService foodService;

	
	@Autowired
	private FoodOrderService foodOrderService;
	
	@PostMapping("/create-order")
	public ResponseEntity<CommonResponseBean> saveTrain(@RequestBody PassengerBean bean) {
		CommonResponseBean response = foodOrderService.createOrder(bean);
		if (response.getStatus()) {
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
		}
	}
	
	@PostMapping("/add-food")
	public ResponseEntity<CommonResponseBean> addFood(@RequestBody PassengerBean bean) {
		CommonResponseBean response = foodService.addFood(bean);
		if (response.getStatus()) {
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
		}
	}
	
	@GetMapping("/getOrderPriceByDateAndTrainId")
	public ResponseEntity<CommonResponseBean> getOrderPriceByDateAndTrainId(@RequestParam String date,@RequestParam Long trainId ) {
		CommonResponseBean response = foodOrderService.getOrderPriceByDateAndTrainId(date,trainId);
		if (response.getStatus()) {
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
		}
	}
	
}
