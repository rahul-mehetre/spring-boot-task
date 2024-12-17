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
import com.ircts.service.PassengerService;
import com.ircts.service.TrainService;

@RestController
@RequestMapping("/api/irctc")
public class TrainController {

	@Autowired
	private TrainService trainService;
	
	@Autowired
	private PassengerService passengerService;

	@PostMapping("/save")
	public ResponseEntity<CommonResponseBean> saveTrain(@RequestBody PassengerBean bean) {
		CommonResponseBean response = trainService.saveTrain(bean);
		if (response.getStatus()) {
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
		}
	}
	
	@GetMapping("/getTrainById")
	public ResponseEntity<CommonResponseBean> getTrain(@RequestParam Long trainId){
		CommonResponseBean response = trainService.getTrain(trainId);
		if(response.getStatus()) {
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		}else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
		}
	}
	
	
	@PostMapping("/add-passenger")
	public ResponseEntity<CommonResponseBean> savePassenger(@RequestBody PassengerBean bean) {
		CommonResponseBean response = passengerService.savePassenger(bean);
		if (response.getStatus()) {
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
		}
	}
	
	@GetMapping("/getPassengerById")
	public ResponseEntity<CommonResponseBean> getPassengerById(@RequestParam Long passengerId){
		CommonResponseBean response = passengerService.getPassenger(passengerId);
		if(response.getStatus()) {
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		}else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
		}
	}
	
	@PostMapping("/getPassengerCount-By-Source-Des-Date")
	public ResponseEntity<CommonResponseBean> getPassCount(@RequestBody PassengerBean bean){
		CommonResponseBean response = passengerService.getPassengerCountByCityNamesAndDate(bean);
		if(response.getStatus()) {
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		}else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
		}
	}
	
	@GetMapping("/getFemalPassengerCount")
	public ResponseEntity<CommonResponseBean> getFemalPassengerCount(@RequestParam Integer start_age,@RequestParam Integer end_age,@RequestParam String source){
		CommonResponseBean response = passengerService.getFemalePassengerCountByAge(start_age, end_age, source);
		if(response.getStatus()) {
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		}else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
		}
	}
}
