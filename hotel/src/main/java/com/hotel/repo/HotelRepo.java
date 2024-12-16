package com.hotel.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotel.entities.Hotel;

@Repository
public interface HotelRepo extends  JpaRepository<Hotel, Long> {

}
