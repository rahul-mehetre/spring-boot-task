package com.hotel.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Room {

	@Id
	private Long roomId;
	
	private Boolean isAcRoom; // Y/N
	
	private Double roomPrice;
	
	private Boolean isBooked;
	
	private Boolean isActive;
	
	@ManyToOne
	@JsonBackReference
	private Hotel hotel;
	
	
}
