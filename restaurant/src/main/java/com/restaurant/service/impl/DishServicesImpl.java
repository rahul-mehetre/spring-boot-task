package com.restaurant.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurant.beans.CommonResponse;
import com.restaurant.beans.DishBean;
import com.restaurant.entities.DishEntity;
import com.restaurant.repo.DishRepo;
import com.restaurant.service.DishServices;

@Service
public class DishServicesImpl implements DishServices {

	@Autowired
	private DishRepo dishRepo;

	@Override
	public CommonResponse saveDish(DishBean bean) {
		CommonResponse response = new CommonResponse();
		DishEntity entity = mapBeanToEntity(bean);
		if (entity != null) {
			DishEntity savedEntity = dishRepo.save(entity);
			if (savedEntity != null) {
				response.setStatus(true);
				response.setMessage("New Dish Added..");
				response.setResponseBean(savedEntity);
			} else {
				response.setStatus(false);
				response.setMessage("Unable to saved data");
			}

		} else {
			response.setStatus(false);
			response.setMessage("Please enter valid Data");
		}
		return response;
	}

	@Override
	public CommonResponse getMenuCard() {
		CommonResponse response = new CommonResponse();
		List<DishEntity> savedEntity = dishRepo.findAll();
		if (savedEntity != null && savedEntity.size() > 0) {
			response.setStatus(true);
			response.setMessage("New Dish Added..");
			response.setResponseBean(savedEntity);
		} else {
			response.setStatus(false);
			response.setMessage("No Dish available");
		}
		return response;
	}

	public DishEntity mapBeanToEntity(DishBean bean) {
		DishEntity entity = new DishEntity();
		if(bean.getDishName() != null) entity.setDishName(bean.getDishName());
		if(bean.getDishPrice() != null) entity.setDishPrice(bean.getDishPrice());
		if(bean.getId() != null) entity.setId(bean.getId());
		return entity;
	}
}
