package com.employee.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.beans.CommonResponseBean;
import com.employee.beans.EmployeeBean;
import com.employee.entities.Employee;
import com.employee.service.EmployeeService;
import com.employee.service.repo.EmployeeRepo;

@Service
public class EmployeeServiceIMPL implements EmployeeService {

	@Autowired
	private EmployeeRepo employeeRepo;

	@Override
	public CommonResponseBean saveEmployee(EmployeeBean bean) {
		CommonResponseBean responseBean = new CommonResponseBean();
		Employee employee = mapBeanToEntity(bean, responseBean);
		if (responseBean.getStatus()) {
			Employee savedEmployee = employeeRepo.save(employee);
			if (savedEmployee != null) {
				responseBean.setStatus(true);
				responseBean.setMessage("Employee Saved Successfully ");
				responseBean.setResponseBean(savedEmployee);
			} else {
				responseBean.setStatus(false);
				responseBean.setMessage("System Error : Unable Saved Employee!");
				responseBean.setResponseBean(null);
			}
		}
		return responseBean;
	}

	@Override
	public CommonResponseBean getEmployeeById(Long id) {
		CommonResponseBean responseBean = new CommonResponseBean();
		if (id != null) {
			Optional<Employee> employee = employeeRepo.findById(id);
			if (employee.isPresent()) {
				responseBean.setStatus(true);
				responseBean.setMessage("Employee Details ");
				responseBean.setResponseBean(employee);
			} else {
				responseBean.setStatus(false);
				responseBean.setMessage("Employee Not Found");
				responseBean.setResponseBean(null);
			}
		}
		return responseBean;
	}

	@Override
	public CommonResponseBean getEmployeeByAgeAndRating(Long age, Long rating) {
		CommonResponseBean responseBean = new CommonResponseBean();
		if (age != null && rating != null) {
			Optional<List<Employee>> employee = employeeRepo.findByAgeAndRating(age, rating);
			if (employee.isPresent()) {
				responseBean.setStatus(true);
				responseBean.setMessage("Employee Details ");
				responseBean.setResponseBean(employee);
			} else {
				responseBean.setStatus(false);
				responseBean.setMessage("Employee Not Found");
				responseBean.setResponseBean(null);
			}
		}
		return responseBean;
	}

	@Override
	public CommonResponseBean updateBossSalByRating(Double updateSal, Long rating) {
		CommonResponseBean responseBean = new CommonResponseBean();
		if (updateSal != null && rating != null) {
			Integer employee = employeeRepo.updateEmployeeSalByRating(updateSal, rating);
			if (employee != null) {
				responseBean.setStatus(true);
				responseBean.setMessage("Employee Boss Salary Updated ");
				responseBean.setResponseBean(employee + " Boss Employee Salary Updated");
			} else {
				responseBean.setStatus(false);
				responseBean.setMessage("Unable To Upadte Employee Boss Salary");
				responseBean.setResponseBean(null);
			}
		}
		return responseBean;
	}

	@Override
	public CommonResponseBean getEmployeeCountByBossRatingAndEmployeeRating(Long rating) {
		CommonResponseBean responseBean = new CommonResponseBean();
		if (rating != null) {
			Optional<List<Employee>> employee = employeeRepo.findByBossAndEmployeeRatingCriteriaOne(rating);
			if (employee.isPresent()) {
				responseBean.setStatus(true);
				responseBean.setMessage(employee.get().size() + " Records Found.");
				responseBean.setResponseBean(employee.get());
			} else {
				responseBean.setStatus(false);
				responseBean.setMessage("Unable To Upadte Employee Boss Salary");
				responseBean.setResponseBean(null);
			}
		}
		return responseBean;
	}

	@Override
	public Employee mapBeanToEntity(EmployeeBean bean, CommonResponseBean responseBean) {
		Employee emp = new Employee();
		if (bean.getEmployeeId() != null)
			emp.setId(bean.getEmployeeId());
		if (bean.getEmployeeName() != null)
			emp.setName(bean.getEmployeeName());
		if (bean.getEmployeePosition() != null)
			emp.setPosition(bean.getEmployeePosition().toLowerCase());
		if (bean.getAge() != null)
			emp.setAge(bean.getAge());
		if (bean.getRating() != null)
			emp.setRating(bean.getRating());
		if (bean.getSalary() != null)
			emp.setSalary(bean.getSalary());

		if (!bean.getEmployeePosition().equalsIgnoreCase("CEO")) {
			if (bean.getBossEmployeeId() != null) {
				Optional<Employee> bossEmployee = employeeRepo.findById(bean.getBossEmployeeId());
				if (bossEmployee.isPresent()) {
					emp.setBoss(bossEmployee.get());
					responseBean.setStatus(true);
				}
			} else {
				responseBean.setStatus(false);
				responseBean.setMessage("Boss Employee Not Found");
			}
		} else {
			responseBean.setStatus(true);
		}
		return emp;
	}

}
