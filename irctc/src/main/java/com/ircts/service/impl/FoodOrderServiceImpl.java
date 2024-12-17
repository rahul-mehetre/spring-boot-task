package com.ircts.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ircts.beans.CommonResponseBean;
import com.ircts.beans.PassengerBean;
import com.ircts.entities.Food;
import com.ircts.entities.FoodOrder;
import com.ircts.entities.Passenger;
import com.ircts.repo.FoodOrderRepo;
import com.ircts.repo.FoodRepo;
import com.ircts.repo.PassengerRepo;
import com.ircts.service.FoodOrderService;

@Service
public class FoodOrderServiceImpl implements FoodOrderService {

	@Autowired
	private FoodOrderRepo foodOrderRepo;

	@Autowired
	private FoodRepo foodRepo;

	@Autowired
	private PassengerRepo passengerRepo;

	@Override
	public CommonResponseBean createOrder(PassengerBean bean) {
		CommonResponseBean response = new CommonResponseBean();

		FoodOrder order = new FoodOrder();

		if (bean.getPassengerId() != null) {
			Optional<Passenger> passenger = passengerRepo.findById(bean.getPassengerId());
			if (passenger.isPresent()) {
				response.setStatus(true);
				order.setPassenger(passenger.get());
			} else {
				response.setStatus(false);
				response.setMessage("Passenger is not found.");
			}
		} else {
			response.setStatus(false);
			response.setMessage("Passenger Id is Mandatory.");
		}
		List<Food> selectedFoodList = new ArrayList<>();
		Double billAmount = 0d;
		for (Long id : bean.getSelectedFoodIds()) {
			Optional<Food> food = foodRepo.findById(id);
			if (food.isPresent()) {
				selectedFoodList.add(food.get());
				billAmount = billAmount + food.get().getPrice();
			}
		}

		if (selectedFoodList.size() > 0) {
			response.setStatus(true);
			order.setFoodOrders(selectedFoodList);
			order.setBillAmount(billAmount);
			FoodOrder savedOrder = foodOrderRepo.save(order);
			if (savedOrder != null) {
				response.setStatus(true);
				response.setMessage("Order Generated");
				response.setResponseBean(savedOrder);
			} else {
				response.setStatus(false);
				response.setMessage("Orde not Generated");
			}
		} else {
			response.setStatus(false);
			response.setMessage("No food is selectd");
		}

		return response;
	}

	@Override
	public CommonResponseBean getOrder(Long orderId) {
		CommonResponseBean response = new CommonResponseBean();
		Optional<FoodOrder> savedOrder = foodOrderRepo.findById(orderId);
		if (savedOrder.isPresent()) {
			response.setStatus(true);
			response.setMessage("Order Details");
			response.setResponseBean(savedOrder);
		} else {
			response.setStatus(false);
			response.setMessage("Orde not found");
		}

		return response;
	}

	@Override
	public CommonResponseBean updateOrder(Long orderId, PassengerBean bean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommonResponseBean closeOrder(Long orderId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommonResponseBean getOrderPriceByDateAndTrainId(String date, Long trainId) {
		CommonResponseBean response = new CommonResponseBean();
		Double entity = foodOrderRepo.getTotalPrice(date,trainId);
		if(entity != null) {
			response.setStatus(true);
			response.setMessage("Total Price : "+entity);
			response.setResponseBean(entity);
		} else {
			response.setStatus(false);
			response.setMessage("Unable To Process");
		}
		return response;
	}

}
