package com.ircts.service;

import org.springframework.stereotype.Service;

import com.ircts.beans.CommonResponseBean;
import com.ircts.beans.PassengerBean;

@Service
public interface FoodService {

	public CommonResponseBean addFood(PassengerBean bean);
	
	public CommonResponseBean getFoodList();
	
	public CommonResponseBean getFoodById(Long id);
}
