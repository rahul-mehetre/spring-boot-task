package com.ircts.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class FoodOrder {

	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Long orderId;
	
	@OneToOne
	private Passenger passenger;
	
	private Double billAmount;
	
	@ManyToMany
	private List<Food> foodOrders;
	
	private Boolean isActive;
	
}
