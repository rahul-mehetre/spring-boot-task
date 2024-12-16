package com.hotel.beans;

import lombok.Data;

@Data
public class RoomBean {

	private Long roomId;
	
	private Boolean isAcRoom; // Y/N
	
	private Double roomPrice;
	
	private Boolean isBooked;
	
	private Long hotelId;
	
	private Boolean isActive;
}
