package com.payment.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Transaction {

	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Long transId;
	
	private Double amount;
	
	@ManyToOne
	@JsonBackReference
	private User user;
	
	private Boolean isSuccess;
}
