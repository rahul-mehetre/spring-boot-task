package com.hotel.service;

import org.springframework.stereotype.Service;

import com.hotel.beans.CommonResponse;
import com.hotel.beans.RoomBean;


@Service
public interface RoomService {

	public CommonResponse saveRoom(RoomBean bean);
	
	public CommonResponse getRoom(Long roomId);
	
	public CommonResponse deleteRoom(Long roomId);
	
	public CommonResponse bookRoom(Long roomId);
	
	
	public CommonResponse unBookRoom(Long roomId);
	
}
