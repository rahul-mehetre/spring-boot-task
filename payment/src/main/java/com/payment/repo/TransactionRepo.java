package com.payment.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.payment.entities.Transaction;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction,Long>{

	@Query("select sum(t.amount) from Transaction t  where t.user.userId=:transId and t.isSuccess=true")
	public Double findSuccessAmountTotalByUser(Long transId);
	
	
	public Integer deleteByIsSuccessFalse();
	
	
	
}
