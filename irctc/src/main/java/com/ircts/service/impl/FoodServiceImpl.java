package com.ircts.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ircts.beans.CommonResponseBean;
import com.ircts.beans.PassengerBean;
import com.ircts.entities.Food;
import com.ircts.repo.FoodRepo;
import com.ircts.service.FoodService;

@Service
public class FoodServiceImpl implements FoodService{

	@Autowired
	private FoodRepo foodRepo;
	
	@Override
	public CommonResponseBean addFood(PassengerBean bean) {
		CommonResponseBean response = new CommonResponseBean();
		Food food =new Food();
		food.setName(bean.getName());
		food.setPrice(bean.getPrice());
		Food saved = foodRepo.save(food);
		if(saved != null) {
			response.setStatus(true);
			response.setMessage("Food Saved");
			response.setResponseBean(saved);
		}else {
			response.setStatus(false);
			response.setMessage("Food not saved.");
		}
		return response;
	}

	@Override
	public CommonResponseBean getFoodList() {
		CommonResponseBean response = new CommonResponseBean();
		List<Food> saved = foodRepo.findAll();
		if(saved.size()>0) {
			response.setStatus(true);
			response.setMessage("Food List");
			response.setResponseBean(saved);
		}else {
			response.setStatus(false);
			response.setMessage("No food found.");
		}
		return response;
	}

	@Override
	public CommonResponseBean getFoodById(Long id) {
		CommonResponseBean response = new CommonResponseBean();
	 Optional<Food> saved = foodRepo.findById(id);
		if(saved.isPresent()) {
			response.setStatus(true);
			response.setMessage("Food List");
			response.setResponseBean(saved.get());
		}else {
			response.setStatus(false);
			response.setMessage("No food found.");
		}
		return response;
	}

	
}
