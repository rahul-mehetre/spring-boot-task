package com.ircts.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ircts.entities.Passenger;

public interface PassengerRepo extends JpaRepository<Passenger,Long> {

	
	@Query("select p from Passenger p where p.train.source=:source and p.train.destination=:destination and p.date=:date")
	public List<Passenger> findByTrainIdSourceAndTrainIdDestinationAndDate(String source,String destination,String date);
	
	
	@Query("select p from Passenger p where p.train.source=:source and p.age between :start and :end")
	public List<Passenger> findFemalePassengerCount(Integer start,Integer end,String source);
}
