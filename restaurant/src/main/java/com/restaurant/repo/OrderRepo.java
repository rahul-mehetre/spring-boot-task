package com.restaurant.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restaurant.entities.OrderEntity;

public interface OrderRepo extends JpaRepository<OrderEntity ,Long>{

	List<OrderEntity> findByTableNoAndIsActiveTrue(Long tableNo);

}
