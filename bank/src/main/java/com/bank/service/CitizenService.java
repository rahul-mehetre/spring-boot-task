package com.bank.service;

import org.springframework.stereotype.Service;

import com.bank.beans.CitizenBean;
import com.bank.beans.CommonResponseBean;

@Service
public interface CitizenService {

	public CommonResponseBean saveCitizen(CitizenBean bean);
	public CommonResponseBean getCitizenByAadhar(String aadharNo);
	
	public CommonResponseBean getCititzenByPan(String panCardNo);
	
	public CommonResponseBean updatePersonNameByAadhar(String aadharNo, String name);
	
}
