package com.ircts.service;

import org.springframework.stereotype.Service;

import com.ircts.beans.CommonResponseBean;
import com.ircts.beans.PassengerBean;

@Service
public interface TrainService {

	public CommonResponseBean saveTrain(PassengerBean bean);
	public CommonResponseBean getTrain(Long id);
}
