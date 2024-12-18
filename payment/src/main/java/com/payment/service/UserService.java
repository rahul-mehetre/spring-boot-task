package com.payment.service;

import org.springframework.stereotype.Service;

import com.payment.beans.CommonResponseBean;
import com.payment.beans.TransactionBean;
import com.payment.beans.UserBean;

@Service
public interface UserService {

	public CommonResponseBean saveUser(UserBean user);
	
	public CommonResponseBean  createTransaction(TransactionBean bean);
	
	public CommonResponseBean getSuccessAmountByUser(Long userId);
	public CommonResponseBean deleteAllFailTransaction();
	public CommonResponseBean getUserIdWithMaxRefundAmount();
}
