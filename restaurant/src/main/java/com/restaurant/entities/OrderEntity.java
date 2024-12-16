package com.restaurant.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class OrderEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long orderId;
	
	@ManyToMany
	private List<DishEntity> selectedDishesId;
	
	private Boolean isActive;
	
	private Long tableNo;
	
	private Double billAmount;
	
}
