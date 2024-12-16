package com.restaurant.service;

import org.springframework.stereotype.Service;

import com.restaurant.beans.CommonResponse;
import com.restaurant.beans.OrderBean;

@Service
public interface OrderService {

	public CommonResponse createOrder(OrderBean bean);
	public CommonResponse getOrder(Long orderId);
	
	public CommonResponse addDishInOrder(OrderBean bean,Long tableNo);
	public CommonResponse getBillByTableNo(Long tableNo);
	public CommonResponse closeOrder(Long tableNo);
}
