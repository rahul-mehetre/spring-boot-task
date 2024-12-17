package com.ircts.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ircts.beans.CommonResponseBean;
import com.ircts.beans.PassengerBean;
import com.ircts.entities.Train;
import com.ircts.repo.TrainRepo;
import com.ircts.service.TrainService;

@Service
public class TrainServiceImpl implements TrainService {

	@Autowired
	private TrainRepo trainRepo;

	@Override
	public CommonResponseBean saveTrain(PassengerBean bean) {
		CommonResponseBean response = new CommonResponseBean();
		Train train = new Train();
		train.setDestination(bean.getDestination());
		train.setSource(bean.getSource());
		Train saved = trainRepo.save(train);
		if (saved != null) {
			response.setMessage("Train Added");
			response.setStatus(true);
			response.setResponseBean(saved);
		} else {
			response.setMessage("Train  not Added");
			response.setStatus(false);
		}
		return response;
	}

	@Override
	public CommonResponseBean getTrain(Long id) {
		CommonResponseBean response = new CommonResponseBean();
		Optional<Train> saved = trainRepo.findById(id);
		if (saved.isPresent()) {
			response.setMessage("Train Details");
			response.setStatus(true);
			response.setResponseBean(saved.get());
		} else {
			response.setMessage("Train not found");
			response.setStatus(false);
		}
		return response;
	}

}
