package com.employee.beans;

import lombok.Data;

@Data
public class EmployeeBean {

	
	private Long employeeId;
	
	private String employeeName;
	
	private String employeePosition;
	
	private Long rating;
	
	private Long age;
	
	private Double salary;
	
	private Long bossEmployeeId;
	
}
