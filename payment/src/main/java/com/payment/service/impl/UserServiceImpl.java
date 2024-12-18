package com.payment.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payment.beans.CommonResponseBean;
import com.payment.beans.TransactionBean;
import com.payment.beans.UserBean;
import com.payment.entities.Refund;
import com.payment.entities.Transaction;
import com.payment.entities.User;
import com.payment.repo.RefundRepo;
import com.payment.repo.TransactionRepo;
import com.payment.repo.UserRepo;
import com.payment.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private TransactionRepo transactionRepo;

	@Autowired
	private RefundRepo refundRepo;

	@Override
	public CommonResponseBean saveUser(UserBean user) {
		CommonResponseBean response = new CommonResponseBean();
		User entity = new User();
		entity.setName(user.getName());
		entity.setEmail(user.getEmail());
		entity.setAccNo(user.getAccNo());

		User saved = userRepo.save(entity);
		if (saved != null) {
			response.setStatus(true);
			response.setMessage("User Created");
			response.setResponseBean(saved);
		} else {
			response.setStatus(false);
			response.setMessage("Unable to create user");
		}
		return response;
	}

	@Override
	public CommonResponseBean createTransaction(TransactionBean bean) {
		CommonResponseBean response = new CommonResponseBean();
		Transaction entity = new Transaction();
		entity.setAmount(bean.getAmount());
		if (bean.getUserId() != null) {
			Optional<User> user = userRepo.findById(bean.getUserId());
			if (user.isPresent()) {
				response.setStatus(true);
				entity.setUser(user.get());
			} else {
				response.setMessage("User Not Found");
				response.setStatus(false);
			}
		}
		entity.setIsSuccess(bean.getIsSuccess());
		if (response.getStatus()) {
			Transaction saved = transactionRepo.save(entity);
			if (saved != null) {
				response.setStatus(true);
				response.setMessage("Success");
				response.setResponseBean(saved);
			} else {
				response.setStatus(false);
			}

			// for refund
			if (saved != null && !saved.getIsSuccess()) {
				Refund refund = new Refund();
				refund.setAmount(saved.getAmount());
				refund.setTransaction(saved);
				refund.setUser(saved.getUser());
				Refund savedRefund = refundRepo.save(refund);
				response.setStatus(false);
				response.setMessage("Transaction Failed");
				response.setResponseBean(savedRefund);
			}
		}
		return response;
	}

	public CommonResponseBean getSuccessAmountByUser(Long userId) {
		CommonResponseBean response = new CommonResponseBean();
		Double transaction = transactionRepo.findSuccessAmountTotalByUser(userId);
		if (transaction != null) {
			response.setStatus(true);
			response.setResponseBean(transaction);
		} else {
			response.setStatus(false);
		}
		return response;
	}

	public CommonResponseBean deleteAllFailTransaction() {
		CommonResponseBean response = new CommonResponseBean();
		Integer transaction = transactionRepo.deleteByIsSuccessFalse();
		if (transaction != null) {
			response.setStatus(true);
			response.setResponseBean(transaction);
		} else {
			response.setStatus(false);
		}
		return response;
	}
	
	
	public CommonResponseBean getUserIdWithMaxRefundAmount() {
		CommonResponseBean response = new CommonResponseBean();
		Long transaction = refundRepo.getUserIdWithMaxRefundAmount();
		if (transaction != null) {
			response.setStatus(true);
			response.setResponseBean(transaction);
		} else {
			response.setStatus(false);
		}
		return response;
	}

}
