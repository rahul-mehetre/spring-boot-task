package com.hotel.service;

import org.springframework.stereotype.Service;

import com.hotel.beans.CommonResponse;
import com.hotel.beans.HotelBean;


@Service
public interface HotelService {

	public CommonResponse saveHotel(HotelBean bean);
	
	public CommonResponse getHotel(Long id);
	
}
