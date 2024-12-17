package com.ircts.service;

import org.springframework.stereotype.Service;

import com.ircts.beans.CommonResponseBean;
import com.ircts.beans.PassengerBean;

@Service
public interface FoodOrderService {

	public CommonResponseBean createOrder(PassengerBean bean);
	
	public CommonResponseBean getOrder(Long orderId);
	
	public CommonResponseBean updateOrder(Long orderId , PassengerBean bean);
	
	public CommonResponseBean closeOrder(Long orderId);
	
	
	public CommonResponseBean getOrderPriceByDateAndTrainId(String date ,Long trainId);
}
