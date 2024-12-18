package com.payment.beans;

import java.util.List;

import lombok.Data;

@Data
public class UserBean {

	private Long userId;
	
	private String name;
	
	private String email;
	
	private String accNo;
	
	private List<Long> transIds;
}
