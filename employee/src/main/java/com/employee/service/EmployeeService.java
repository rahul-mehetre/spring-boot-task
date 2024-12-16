package com.employee.service;

import org.springframework.stereotype.Service;

import com.employee.beans.CommonResponseBean;
import com.employee.beans.EmployeeBean;
import com.employee.entities.Employee;


@Service
public interface EmployeeService {

	
	public CommonResponseBean saveEmployee(EmployeeBean bean);
	
	public CommonResponseBean getEmployeeById(Long id);
	
	public CommonResponseBean getEmployeeByAgeAndRating(Long age,Long rating);
	
	public CommonResponseBean updateBossSalByRating(Double updateSal , Long rating);
	
	public CommonResponseBean getEmployeeCountByBossRatingAndEmployeeRating(Long rating);

	public Employee mapBeanToEntity(EmployeeBean bean,CommonResponseBean responseBean);
}
