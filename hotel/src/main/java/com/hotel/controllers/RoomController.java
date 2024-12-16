package com.hotel.controllers;

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

import com.hotel.beans.CommonResponse;
import com.hotel.beans.RoomBean;
import com.hotel.service.RoomService;

@RestController
@RequestMapping("/api/hotel/room")
public class RoomController {
	
	@Autowired 
	private RoomService roomService;
	
	@PostMapping("/save")
	public ResponseEntity<CommonResponse> addRoom(@RequestBody RoomBean bean){
		CommonResponse response = roomService.saveRoom(bean);
		if (response.getStatus()) {
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
		}
	}
	
	@GetMapping("/get-room/{roomId}")
	public ResponseEntity<CommonResponse> getRoomById(@PathVariable Long roomId) {
		CommonResponse response =roomService.getRoom(roomId);
		if (response.getStatus()) {
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
		}
	}
	
	@DeleteMapping("/delete-room/{roomId}")
	public ResponseEntity<CommonResponse> deleteRoomById(@PathVariable Long roomId) {
		CommonResponse response =roomService.deleteRoom(roomId);
		if (response.getStatus()) {
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
		}
	}
	
	@PutMapping("/book-room/{roomId}")
	public ResponseEntity<CommonResponse> bookRoom(@PathVariable Long roomId) {
		CommonResponse response =roomService.bookRoom(roomId);
		if (response.getStatus()) {
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
		}
	}
	
	@PutMapping("/unbook-room/{roomId}")
	public ResponseEntity<CommonResponse> unBookRoom(@PathVariable Long roomId) {
		CommonResponse response =roomService.bookRoom(roomId);
		if (response.getStatus()) {
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
		}
	}
	
	
}
