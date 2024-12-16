package com.restaurant.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurant.beans.CommonResponse;
import com.restaurant.beans.OrderBean;
import com.restaurant.entities.DishEntity;
import com.restaurant.entities.OrderEntity;
import com.restaurant.repo.DishRepo;
import com.restaurant.repo.OrderRepo;
import com.restaurant.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private DishRepo dishRepo;

	@Autowired
	private OrderRepo orderRepo;

	@Override
	public CommonResponse createOrder(OrderBean bean) {
		CommonResponse response = new CommonResponse();
		OrderEntity entity = mapBeanToEntity(bean, response);
		if (response.getStatus()) {
			OrderEntity savedEnitity = orderRepo.save(entity);
			if (savedEnitity != null) {
				response.setStatus(true);
				response.setMessage("Order Generated Successfully");
				response.setResponseBean(savedEnitity);
			} else {
				response.setStatus(false);
				response.setMessage("Error : Unable to Generate Order.");
			}
		}
		return response;
	}

	@Override
	public CommonResponse getOrder(Long orderId) {
		CommonResponse response = new CommonResponse();
		if (orderId != null) {
			Optional<OrderEntity> savedEnitity = orderRepo.findById(orderId);
			if (savedEnitity.isPresent()) {
				response.setStatus(true);
				response.setMessage("Order Details");
				response.setResponseBean(savedEnitity.get());
			} else {
				response.setStatus(false);
				response.setMessage("Order Not Found");
			}
		} else {
			response.setStatus(false);
			response.setMessage("Error : Enter Order Id.");
		}
		return response;
	}

	public OrderEntity mapBeanToEntity(OrderBean bean, CommonResponse response) {
		OrderEntity entity = new OrderEntity();
		entity.setBillAmount(bean.getBillAmount());
		entity.setIsActive(bean.getIsActive());
		entity.setOrderId(bean.getOrderId());

		if (bean.getTableNo() != null) {
			List<OrderEntity> savedData = orderRepo.findByTableNoAndIsActiveTrue(bean.getTableNo());
			if (savedData != null && savedData.size() > 0) {
				response.setStatus(false);
				response.setMessage("Order Alredy Present on Table");
				return null;
			} else {
				entity.setTableNo(bean.getTableNo());
			}

		}

		Double billAmount = 0d;
		List<DishEntity> dishList = new ArrayList<>();
		if (bean.getSelectedDishesId() != null && bean.getSelectedDishesId().size() > 0) {
			for (Long dishId : bean.getSelectedDishesId()) {
				Optional<DishEntity> dish = dishRepo.findById(dishId);
				if (dish.isPresent()) {
					dishList.add(dish.get());
					billAmount = billAmount + dish.get().getDishPrice();
				}
			}
			if (dishList.size() > 0) {
				entity.setSelectedDishesId(dishList);
				entity.setBillAmount(billAmount);
			} else {
				response.setStatus(false);
				response.setMessage("Error : Please Enter Valid Dish || No Dish Select ");
				return null;
			}
		} else {
			response.setStatus(false);
			response.setMessage("Error : No Dish Selected.");
			return null;
		}
		response.setStatus(true);
		return entity;
	}

	@Override
	public CommonResponse addDishInOrder(OrderBean bean, Long tableNo) {
		CommonResponse response = new CommonResponse();
		List<OrderEntity> savedData = orderRepo.findByTableNoAndIsActiveTrue(tableNo);
		if (savedData != null && savedData.size() > 0) {
			OrderEntity entity = mapBeanToEntity(bean, response);
			if (response.getStatus()) {

				savedData.get(0).getSelectedDishesId().addAll(entity.getSelectedDishesId());
				Double billAmount = savedData.get(0).getBillAmount() + entity.getBillAmount();
				savedData.get(0).setBillAmount(billAmount);
				OrderEntity savedEnitity = orderRepo.save(savedData.get(0));
				if (savedEnitity != null) {
					response.setStatus(true);
					response.setMessage("Order Updated Successfully");
					response.setResponseBean(savedEnitity);
				} else {
					response.setStatus(false);
					response.setMessage("Error : Unable to Update Order.");
				}
			}

		} else {
			response.setStatus(false);
			response.setMessage("Error : Order Not Found");
		}
		return response;
	}

	@Override
	public CommonResponse getBillByTableNo(Long tableNo) {
		CommonResponse response = new CommonResponse();
		List<OrderEntity> savedData = orderRepo.findByTableNoAndIsActiveTrue(tableNo);
		if (savedData != null && savedData.size() > 0) {
			response.setStatus(true);
			response.setMessage("Order Bill Amount");
			response.setResponseBean(savedData.get(0).getBillAmount());
		} else {
			response.setStatus(true);
			response.setMessage("Order Not Found");
			response.setResponseBean(null);
		}
		return response;
	}

	@Override
	public CommonResponse closeOrder(Long tableNo) {
		CommonResponse response = new CommonResponse();
		List<OrderEntity> savedData = orderRepo.findByTableNoAndIsActiveTrue(tableNo);
		if (savedData != null && savedData.size() > 0) {
			savedData.get(0).setIsActive(false);
			OrderEntity savedEnitity = orderRepo.save(savedData.get(0));
			if (savedEnitity != null) {
				response.setStatus(true);
				response.setMessage("Order Deletd Successfully");
				response.setResponseBean(savedEnitity);
			} else {
				response.setStatus(false);
				response.setMessage("Error : Unable to Delete Order.");
			}

		} else {
			response.setStatus(false);
			response.setMessage(" Order Not Found");
		}
		return response;
	}

}
