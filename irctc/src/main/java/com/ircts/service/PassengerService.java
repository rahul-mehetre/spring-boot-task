package com.ircts.service;

import org.springframework.stereotype.Service;

import com.ircts.beans.CommonResponseBean;
import com.ircts.beans.PassengerBean;

@Service
public interface PassengerService {

	public CommonResponseBean savePassenger(PassengerBean bean);
	public CommonResponseBean getPassenger(Long id);

	
	public CommonResponseBean getPassengerCountByCityNamesAndDate(PassengerBean bean);
	
	public CommonResponseBean getFemalePassengerCountByAge(Integer start,Integer end ,String destination);
}
