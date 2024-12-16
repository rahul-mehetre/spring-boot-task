package com.bank.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.beans.CitizenBean;
import com.bank.beans.CommonResponseBean;
import com.bank.entities.Citizen;
import com.bank.repo.CitizenRepo;
import com.bank.service.CitizenService;

@Service
public class CitizenRepoIMPL implements CitizenService {

	@Autowired
	private CitizenRepo citizenRepo;

	@Override
	public CommonResponseBean saveCitizen(CitizenBean bean) {
		CommonResponseBean responseBean = new CommonResponseBean();
		Citizen entity = mapBeanToEntity(bean);
		Citizen savedEntity = citizenRepo.save(entity);
		if (savedEntity != null) {
			responseBean.setStatus(true);
			responseBean.setMessage("Citizen saved successfully ");
			responseBean.setResponseBean(savedEntity);
		} else {
			responseBean.setStatus(false);
			responseBean.setMessage("Unable to saved citizen");
		}
		return responseBean;
	}

	@Override
	public CommonResponseBean getCitizenByAadhar(String aadharNo) {
		CommonResponseBean responseBean = new CommonResponseBean();
		Optional<Citizen> res = citizenRepo.findByAadharId(aadharNo);
		CitizenBean bean = mapEntityToBean(res.get());
		bean.setAadharId(null);
		if (res.isPresent()) {
			responseBean.setStatus(true);
			responseBean.setMessage("Citizen Details ");
			responseBean.setResponseBean(bean);
		} else {
			responseBean.setStatus(false);
			responseBean.setMessage("citizen not found");
		}
		return responseBean;
	}

	@Override
	public CommonResponseBean getCititzenByPan(String panCardNo) {
		CommonResponseBean responseBean = new CommonResponseBean();
		Optional<Citizen> res = citizenRepo.findByPanId(panCardNo);
		CitizenBean bean = mapEntityToBean(res.get());
		bean.setPanId(null);
		if (res.isPresent()) {
			responseBean.setStatus(true);
			responseBean.setMessage("Citizen Details ");
			responseBean.setResponseBean(bean);
		} else {
			responseBean.setStatus(false);
			responseBean.setMessage("citizen not found");
		}
		return responseBean;
	}

	@Override
	public CommonResponseBean updatePersonNameByAadhar(String aadharNo, String name) {
		CommonResponseBean responseBean = new CommonResponseBean();
		Optional<Citizen> res = citizenRepo.findByAadharId(aadharNo);
		res.get().setName(name);

		if (res.isPresent()) {
			Citizen updatedEntity = citizenRepo.save(res.get());
			if (updatedEntity != null) {
				responseBean.setStatus(true);
				responseBean.setMessage("Citizen Details ");
				responseBean.setResponseBean(mapEntityToBean(updatedEntity));
			} else {
				responseBean.setStatus(false);
				responseBean.setMessage("citizen not updated");
			}

		} else {
			responseBean.setStatus(false);
			responseBean.setMessage("citizen not found");
		}
		return responseBean;
	}

	public Citizen mapBeanToEntity(CitizenBean bean) {
		Citizen city = new Citizen();
		if (bean.getId() != null) {
			city.setId(bean.getId());
		}
		if (bean.getAadharId() != null) {
			city.setAadharId(bean.getAadharId());
		}
		if (bean.getName() != null) {
			city.setName(bean.getName());
		}
		if (bean.getPanId() != null) {
			city.setPanId(bean.getPanId());
		}

		return city;
	}

	public CitizenBean mapEntityToBean(Citizen entity) {
		CitizenBean bean = new CitizenBean();
		bean.setId(entity.getId());
		bean.setName(entity.getName());
		bean.setPanId(entity.getPanId());
		bean.setAadharId(entity.getAadharId());
		return bean;
	}
}
