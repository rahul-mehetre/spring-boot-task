package com.hotel.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.beans.CommonResponse;
import com.hotel.beans.RoomBean;
import com.hotel.entities.Hotel;
import com.hotel.entities.Room;
import com.hotel.repo.HotelRepo;
import com.hotel.repo.RoomRepo;
import com.hotel.service.RoomService;

@Service
public class RoomServiceImpl implements RoomService {

	@Autowired
	private HotelRepo hotelRepo;

	@Autowired
	private RoomRepo roomRepo;

	@Override
	public CommonResponse saveRoom(RoomBean bean) {
		CommonResponse responseBean = new CommonResponse();
		Room room = mapBeanToEntity(bean, responseBean);
		if (responseBean.getStatus()) {
			Room savedRoom = roomRepo.save(room);
			if (savedRoom != null) {
				responseBean.setStatus(true);
				responseBean.setMessage("Room Added Successfully.");
				responseBean.setResponseBean(savedRoom);
			} else {
				responseBean.setStatus(false);
				responseBean.setMessage("Unable To Add Room");
			}
		}
		return responseBean;
	}

	@Override
	public CommonResponse getRoom(Long roomId) {
		CommonResponse responseBean = new CommonResponse();
		if (roomId != null) {
			Room savedRoom = roomRepo.findByRoomIdAndIsActiveTrue(roomId);
			if (savedRoom != null) {
				responseBean.setStatus(true);
				responseBean.setMessage("Room Found.");
				responseBean.setResponseBean(savedRoom);
			} else {
				responseBean.setStatus(false);
				responseBean.setMessage("Room Not Found");
			}
		}
		return responseBean;
	}

	@Override
	public CommonResponse deleteRoom(Long roomId) {
		CommonResponse existingRoom = getRoom(roomId);
		if (existingRoom.getStatus()) {
			Room room = (Room) existingRoom.getResponseBean();
			if (room.getIsActive()) {
				room.setIsActive(false);
				Room updatedResult = roomRepo.save(room);
				if (updatedResult != null) {
					existingRoom.setStatus(true);
					existingRoom.setMessage("Room Updated Successfully.");
					existingRoom.setResponseBean(updatedResult);
				} else {
					existingRoom.setStatus(false);
					existingRoom.setMessage("Room Alredy Deleted.");
				}
			} else {
				existingRoom.setStatus(false);
				existingRoom.setMessage("Room Alredy Deleted.");

			}
		} else {
			return existingRoom;
		}
		return existingRoom;
	}

	@Override
	public CommonResponse bookRoom(Long roomId) {
		CommonResponse existingRoom = getRoom(roomId);
		if (existingRoom != null &&existingRoom.getStatus()) {
			Room room = (Room) existingRoom.getResponseBean();
			if (room.getIsBooked()) {
				existingRoom.setStatus(false);
				existingRoom.setMessage("Room Alredy Booked... Check Another Room ");
				Room updatedResult = roomRepo.save(room);
				if (updatedResult != null) {
					existingRoom.setStatus(true);
					existingRoom.setMessage("Room Updated Successfully.");
					existingRoom.setResponseBean(updatedResult);
				} else {
					existingRoom.setStatus(false);
					existingRoom.setMessage("Room Alredy Deleted.");
				}
			} else {
				room.setIsBooked(true);
			}
		} else {
			existingRoom.setStatus(false);
			existingRoom.setMessage("Room Not Found");
		}
		return existingRoom;
	}

	@Override
	public CommonResponse unBookRoom(Long roomId) {
		CommonResponse existingRoom = getRoom(roomId);
		if (existingRoom.getStatus()) {
			Room room = (Room) existingRoom.getResponseBean();
			if (room.getIsBooked()) {
				room.setIsBooked(false);
				Room updatedResult = roomRepo.save(room);
				if (updatedResult != null) {
					existingRoom.setStatus(true);
					existingRoom.setMessage("Room Updated Successfully.");
					existingRoom.setResponseBean(updatedResult);
				} else {
					existingRoom.setStatus(false);
					existingRoom.setMessage("Room Not updated.");
				}
			} else {
				room.setIsBooked(true);
			}
		} else {
			return existingRoom;
		}
		return null;
	}

	public Room mapBeanToEntity(RoomBean bean, CommonResponse responseBean) {
		Room entity = new Room();
		entity.setRoomId(bean.getRoomId());
		if (bean.getRoomPrice() != null)
			entity.setRoomPrice(bean.getRoomPrice());
		if (bean.getIsBooked() != null)
			entity.setIsBooked(bean.getIsBooked());
		if (bean.getIsAcRoom() != null)
			entity.setIsAcRoom(bean.getIsAcRoom());
		if (bean.getIsActive())
			entity.setIsActive(bean.getIsActive());
		if (bean.getHotelId() != null) {
			Optional<Hotel> hotel = hotelRepo.findById(bean.getHotelId());
			if (hotel.isPresent()) {
				responseBean.setStatus(true);
				entity.setHotel(hotel.get());
			} else {
				responseBean.setStatus(false);
				responseBean.setMessage("Hotel Not Found");
				responseBean.setResponseBean(null);
			}
		} else {
			responseBean.setStatus(true);
		}
		return entity;
	}

}
