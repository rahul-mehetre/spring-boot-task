package com.hotel.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.beans.CommonResponse;
import com.hotel.beans.HotelBean;
import com.hotel.entities.Hotel;
import com.hotel.entities.Room;
import com.hotel.repo.HotelRepo;
import com.hotel.repo.RoomRepo;
import com.hotel.service.HotelService;

@Service
public class HotelServiceImpl implements HotelService {

	@Autowired
	private HotelRepo hotelRepo;

	@Autowired
	private RoomRepo roomRepo;

	@Override
	public CommonResponse saveHotel(HotelBean bean) {
		CommonResponse responseBean = new CommonResponse();
		Hotel hotel = mapBeanToEntity(bean, responseBean);
		if (responseBean.getStatus()) {
			Hotel savedHotel = hotelRepo.save(hotel);
			if (savedHotel != null) {
				responseBean.setStatus(true);
				responseBean.setMessage("Hotel Saved Successfully.");
				responseBean.setResponseBean(savedHotel);
			} else {
				responseBean.setStatus(false);
				responseBean.setMessage("Unable To Save Hotel");
			}
		}
		return responseBean;
	}

	@Override
	public CommonResponse getHotel(Long id) {
		CommonResponse responseBean = new CommonResponse();
		if (id != null) {
			Optional<Hotel> savedHotel = hotelRepo.findById(id);
			if (savedHotel.isPresent()) {
				responseBean.setStatus(true);
				responseBean.setMessage("Hotel Found.");
				responseBean.setResponseBean(savedHotel);
			} else {
				responseBean.setStatus(false);
				responseBean.setMessage("Hotel Not Found");
			}
		}
		return responseBean;
	}

	public Hotel mapBeanToEntity(HotelBean bean, CommonResponse responseBean) {
		Hotel entity = new Hotel();
		if (bean.getId() != null)
			entity.setId(bean.getId());
		if (bean.getHotelName() != null)
			entity.setHotelName(bean.getHotelName());
		if (bean.getRoomIdList() != null && bean.getRoomIdList().size() > 0) {
			List<Room> roomList = new ArrayList<>();
			for (Long roomId : bean.getRoomIdList()) {
				Optional<Room> roomEntity = roomRepo.findById(roomId);
				if (roomEntity.isPresent()) {
					roomList.add(roomEntity.get());
				}
			}
			if (roomList.size() > 0)
				entity.setHotelRoom(roomList);
			else
				responseBean.setStatus(false);
		} else {
			responseBean.setStatus(true);
		}

		return entity;
	}

}
