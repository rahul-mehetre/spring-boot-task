package com.ircts.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ircts.entities.Food;

@Repository
public interface FoodRepo extends JpaRepository<Food,Long> {

}
