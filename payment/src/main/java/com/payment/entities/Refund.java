package com.payment.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data 
@Entity
public class Refund {

	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Long refundId;
	
	private Double amount;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private User user;
	
	@OneToOne
	private Transaction transaction;
}
