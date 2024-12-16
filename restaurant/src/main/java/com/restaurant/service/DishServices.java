package com.restaurant.service;

import org.springframework.stereotype.Service;

import com.restaurant.beans.CommonResponse;
import com.restaurant.beans.DishBean;


@Service
public interface DishServices {

	public CommonResponse saveDish(DishBean bean);
	
	public CommonResponse getMenuCard();
	
}
