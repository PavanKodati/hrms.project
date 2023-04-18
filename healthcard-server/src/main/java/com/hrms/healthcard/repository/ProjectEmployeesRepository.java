package com.hrms.healthcard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hrms.healthcard.Entity.ProjectEmployees;

public interface ProjectEmployeesRepository extends JpaRepository<ProjectEmployees, String> {
	@Query(value = "select * from project_employees where employee_id=?1",nativeQuery = true)
	List<ProjectEmployees> findByEmployeeId(String userId);
	
	
	@Query(value = "select * from project_employees where project_id=?1",nativeQuery = true)
	List<ProjectEmployees> findByProjectId(String project_id);

}
