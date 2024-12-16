package com.hotel.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.beans.CommonResponse;
import com.hotel.beans.HotelBean;
import com.hotel.beans.RoomBean;
import com.hotel.service.HotelService;



@RestController
@RequestMapping("/api/hotel")
public class HotelController {

	
	@Autowired
	private HotelService  hotelService;
	
	
	
	@PostMapping("/save")
	public ResponseEntity<CommonResponse> addHotel(@RequestBody HotelBean bean){
		CommonResponse response = hotelService.saveHotel(bean);
		if (response.getStatus()) {
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
		}
	}
	
	@GetMapping("/get-hotel/{roomId}")
	public ResponseEntity<CommonResponse> getHotelById(@PathVariable Long hotelId) {
		CommonResponse response =hotelService.getHotel(hotelId);
		if (response.getStatus()) {
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
		}
	}
	
}
