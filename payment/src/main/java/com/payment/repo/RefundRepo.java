package com.payment.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.payment.entities.Refund;

@Repository
public interface RefundRepo extends JpaRepository<Refund, Long>{

	 @Query("SELECT f.user.userId FROM Refund f " +
	           "GROUP BY f.user.userId " +  
	           "ORDER BY SUM(f.amount) DESC")
	public Long getUserIdWithMaxRefundAmount();
}
