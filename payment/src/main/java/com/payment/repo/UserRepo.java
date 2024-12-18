package com.payment.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.payment.entities.User;

@Repository
public interface UserRepo extends JpaRepository<User,Long>{

}
