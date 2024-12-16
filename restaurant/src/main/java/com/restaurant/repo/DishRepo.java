package com.restaurant.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restaurant.entities.DishEntity;

public interface DishRepo extends JpaRepository<DishEntity, Long> {

}
