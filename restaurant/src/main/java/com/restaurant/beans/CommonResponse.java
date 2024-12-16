package com.restaurant.beans;

import lombok.Data;

@Data
public class CommonResponse {

	private Boolean status;
	
	private String message;
	
	private Object responseBean;
	
}
