package com.ircts.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ircts.beans.CommonResponseBean;
import com.ircts.beans.PassengerBean;
import com.ircts.entities.Passenger;
import com.ircts.entities.Train;
import com.ircts.repo.PassengerRepo;
import com.ircts.repo.TrainRepo;
import com.ircts.service.PassengerService;

@Service
public class PassengerServiceImpl implements PassengerService {

	@Autowired
	private PassengerRepo passengerRepo;

	@Autowired
	private TrainRepo trainRepo;

	@Override
	public CommonResponseBean savePassenger(PassengerBean bean) {
		CommonResponseBean response = new CommonResponseBean();
		Passenger entity = new Passenger();

		entity.setAge(bean.getAge());
		entity.setGender(bean.getGender());
		entity.setTicketId(bean.getTicketId());

		if (bean.getTrainId() != null) {
			Optional<Train> traindetails = trainRepo.findById(bean.getTrainId());
			if (traindetails.isPresent()) {
				response.setStatus(true);
				entity.setTrain(traindetails.get());
			} else {
				response.setStatus(false);
				response.setMessage("Train Not Found");
			}
		} else {
			response.setStatus(false);
			response.setMessage("Train Id is Mandatory");
		}
		LocalDate currentDateTime = LocalDate.now();
		entity.setDate(currentDateTime.toString());

		if (response.getStatus()) {
			Passenger passenger = passengerRepo.save(entity);
			if (passenger != null) {
				response.setStatus(true);
				response.setMessage("Passenger Detail Saved...");
				response.setResponseBean(passenger);
			} else {
				response.setStatus(false);
				response.setMessage("System Error Unable to Process.");
			}
		}
		return response;
	}

	@Override
	public CommonResponseBean getPassenger(Long id) {
		CommonResponseBean response = new CommonResponseBean();
		Optional<Passenger> entity = passengerRepo.findById(id);
		if (entity.isPresent()) {
			response.setStatus(true);
			response.setMessage("Passenger Details");
			response.setResponseBean(entity.get());
		} else {
			response.setStatus(false);
			response.setMessage("Passenger is not present");
		}
		return response;
	}

	@Override
	public CommonResponseBean getPassengerCountByCityNamesAndDate(PassengerBean bean) {
		CommonResponseBean response = new CommonResponseBean();
		List<Passenger> entity = passengerRepo.findByTrainIdSourceAndTrainIdDestinationAndDate(bean.getSource(),bean.getDestination(),bean.getDate());
		if(entity.size()>0) {
			response.setStatus(true);
			response.setMessage("Passenger Count : "+entity.size());
			response.setResponseBean(entity.size());
		} else {
			response.setStatus(false);
			response.setMessage("Unable To Process");
		}
		return response;
	}

	@Override
	public CommonResponseBean getFemalePassengerCountByAge(Integer start, Integer end, String destination) {
		CommonResponseBean response = new CommonResponseBean();
		List<Passenger> entity = passengerRepo.findFemalePassengerCount(start,end,destination);
		if(entity.size()>0) {
			response.setStatus(true);
			response.setMessage("Passenger Count : "+entity.size());
			response.setResponseBean(entity.size());
		} else {
			response.setStatus(false);
			response.setMessage("Unable To Process");
		}
		return response;
	}

}
