package com.book.author.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.book.author.entities.Publish;

@Repository
public interface PublishRepo extends JpaRepository<Publish, Long> {

	
}
