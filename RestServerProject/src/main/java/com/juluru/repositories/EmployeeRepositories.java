package com.juluru.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.juluru.entities.Employee;

@Repository
@Transactional
public interface EmployeeRepositories extends JpaRepository<Employee, Integer> {
	
	List<Employee> findByAgeGreaterThan(Integer age);
	
	
	@Query(value="select a from Employee a")
	Employee QueryData();
	
	

}
