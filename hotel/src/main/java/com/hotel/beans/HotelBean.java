package com.hotel.beans;

import java.util.List;
import lombok.Data;

@Data
public class HotelBean {

	private Long id;
	
	private String hotelName;
	
	private List<Long> roomIdList;
	
	private List<RoomBean> roomBeans;
}
