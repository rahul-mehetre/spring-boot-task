package com.employee.service.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.employee.entities.Employee;

import jakarta.transaction.Transactional;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {

	@Modifying
	@Transactional
	@Query("update Employee e set e.salary=:salary where e.position='boss' and e.rating=:rating ")
	public Integer updateEmployeeSalByRating(@Param("salary") Double salary,@Param("rating") Long rating);
	
	
	public Optional<List<Employee>> findByAgeAndRating(Long age, Long rating);
	
	@Query("select e from Employee e where e.rating > :rating and e.boss.rating > :rating")
	public Optional<List<Employee>> findByBossAndEmployeeRatingCriteriaOne(@Param("rating") Long rating);
}
