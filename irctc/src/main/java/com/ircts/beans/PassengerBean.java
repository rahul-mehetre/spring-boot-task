package com.ircts.beans;

import java.util.List;
import lombok.Data;

@Data
public class PassengerBean {

	private Long id;

	private String name;

	private Double price;

	private Boolean isActive;

	private Long orderId;

	private Long passengerId;

	private Double billAmount;

	private Long trainId;

	private String source;

	private String destination;

	private Long ticketId;

	private String date;

	private Integer age;

	private String gender;
	
	private List<Long > selectedFoodIds;
}
