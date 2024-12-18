package com.payment.beans;

import lombok.Data;

@Data
public class TransactionBean {

	private Long transId;

	private Double amount;
	
	private Long userId;
	
	private Boolean isSuccess;
}
