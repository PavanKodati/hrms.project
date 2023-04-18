package com.hrms.healthcard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hrms.healthcard.Entity.Employee;


public interface UserRepository extends JpaRepository<Employee, Integer> {
	
	
	Employee findByflId(String flId);

	Employee findByEmail(String email);

}
