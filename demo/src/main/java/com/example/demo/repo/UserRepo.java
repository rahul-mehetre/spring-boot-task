package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

	  // Query to get the count of notifications for a specific user
    @Query("SELECT COUNT(n.notificationID) FROM User u " +
           "JOIN u.posts p " +
           "JOIN p.notifications n " +
           "WHERE u.userId = :userId")
    public Long getNotificationCountForUser(Long userId);
}
