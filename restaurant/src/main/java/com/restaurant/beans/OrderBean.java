package com.restaurant.beans;

import java.util.List;

import lombok.Data;

@Data
public class OrderBean {

	private Long orderId;
	
	private List<Long> selectedDishesId;
	
	private Boolean isActive;
	
	private Long tableNo;
	
	private Double billAmount;
}
