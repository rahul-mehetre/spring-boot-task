package com.hotel.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotel.entities.Room;


@Repository
public interface RoomRepo extends JpaRepository<Room,Long> {

	
	public Room findByRoomIdAndIsActiveTrue(Long roomId);
}
