package com.hrms.healthcard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hrms.healthcard.Entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, String> {

}
