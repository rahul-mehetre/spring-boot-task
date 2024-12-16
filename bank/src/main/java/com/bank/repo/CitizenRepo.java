package com.bank.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.entities.Citizen;

public interface CitizenRepo extends JpaRepository<Citizen, Long> {

	public Optional<Citizen> findByAadharId(String aadharId);
	
	public Optional<Citizen> findByPanId(String panId);
	
	
}
