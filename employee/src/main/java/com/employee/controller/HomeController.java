package com.employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.beans.CommonResponseBean;
import com.employee.beans.EmployeeBean;
import com.employee.service.EmployeeService;

@RestController
@RequestMapping("/api/employee")
public class HomeController {

	@Autowired
	private EmployeeService employeeService;

	@PostMapping("/save")
	public ResponseEntity<CommonResponseBean> saveEmployee(@RequestBody EmployeeBean bean) {
		CommonResponseBean response = employeeService.saveEmployee(bean);
		if (response.getStatus()) {
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
		}
	}

	@GetMapping("/getEmployeeById/{employeeId}")
	public ResponseEntity<CommonResponseBean> getEmployeeById(@PathVariable Long employeeId) {
		CommonResponseBean response = employeeService.getEmployeeById(employeeId);
		if (response.getStatus()) {
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
		}
	}

	@GetMapping("/getEmployeeByAgeAndRating/{age}/{rating}")
	public ResponseEntity<CommonResponseBean> getEmployeeByAgeAndRating(@PathVariable Long age,
			@PathVariable Long rating) {
		CommonResponseBean response = employeeService.getEmployeeByAgeAndRating(age, rating);
		if (response.getStatus()) {
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
		}
	}

	@PutMapping("/updateBossSalByRating")
	public ResponseEntity<CommonResponseBean> updateBossSalByRating(@RequestBody EmployeeBean bean) {
		if (bean.getSalary() != null && bean.getRating() != null) {
			CommonResponseBean response = employeeService.updateBossSalByRating(bean.getSalary(), bean.getRating());
			if (response.getStatus()) {
				return ResponseEntity.status(HttpStatus.CREATED).body(response);
			} else {
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
			}
		}
		return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(null);
	}
	
	@GetMapping("/getEmployeeCountByBossRatingAndEmployeeRating/{rating}")
	public ResponseEntity<CommonResponseBean> getEmployeeByAgeAndRating(
			@PathVariable Long rating) {
		CommonResponseBean response = employeeService.getEmployeeCountByBossRatingAndEmployeeRating(rating);
		if (response.getStatus()) {
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
		}
	}
}
