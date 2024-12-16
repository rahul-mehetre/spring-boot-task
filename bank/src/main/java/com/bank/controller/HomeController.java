package com.bank.controller;

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

import com.bank.beans.CitizenBean;
import com.bank.beans.CommonResponseBean;
import com.bank.service.CitizenService;

@RequestMapping("/api/citizen")
@RestController
public class HomeController {

	@Autowired
	private CitizenService citizenService;

	@PostMapping("/save")
	public ResponseEntity<CommonResponseBean> saveCitizen(@RequestBody CitizenBean bean) {
		CommonResponseBean response = citizenService.saveCitizen(bean);
		if (response.getStatus()) {
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
		}
	}

	@GetMapping("/getByAadhar/{id}")
	public ResponseEntity<CommonResponseBean> getCitizenByAadhar(@PathVariable String id) {
		CommonResponseBean response = citizenService.getCitizenByAadhar(id);
		if (response.getStatus()) {
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
		}
	}

	@GetMapping("/getByPan/{id}")
	public ResponseEntity<CommonResponseBean> getCitizenByPan(@PathVariable String id) {
		CommonResponseBean response = citizenService.getCititzenByPan(id);
		if (response.getStatus()) {
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
		}
	}

	@PutMapping("/upadte-citizen-Name/{id}")
	public ResponseEntity<CommonResponseBean> getCitizenByPan(@PathVariable String id, @RequestBody CitizenBean bean) {
		CommonResponseBean response = citizenService.updatePersonNameByAadhar(id, bean.getName());
		if (response.getStatus()) {
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
		}
	}

}
