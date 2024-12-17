package com.ircts.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ircts.entities.FoodOrder;

public interface FoodOrderRepo extends JpaRepository<FoodOrder,Long> {

	@Query("select sum(o.billAmount) from FoodOrder o where o.passenger.date=:date and o.passenger.train.trainId=:trainId")
	public Double getTotalPrice(String date , Long trainId);
}
