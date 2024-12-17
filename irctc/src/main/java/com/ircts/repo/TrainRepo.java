package com.ircts.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ircts.entities.Train;

public interface TrainRepo extends JpaRepository<Train, Long>{

}
