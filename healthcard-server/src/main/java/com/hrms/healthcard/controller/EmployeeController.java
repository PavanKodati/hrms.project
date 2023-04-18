package com.hrms.healthcard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hrms.healthcard.dto.EmployeeDto;
import com.hrms.healthcard.dto.EmployeeMDto;
import com.hrms.healthcard.service.EmployeeService;
import com.hrms.healthcard.urlConfig.RestUrlConfig;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	// Get Details of the current loggedIn user

	@GetMapping(RestUrlConfig.HEALTHCARD_SERVER+RestUrlConfig.ME)
	public EmployeeMDto getEmployeeDetails(HttpServletRequest request) {
		return employeeService.getEmployee(request);
	}

	// mobile api:- To get the details of the employee associated project or rating
	// for the given month and year

	@GetMapping(RestUrlConfig.HEALTHCARD_SERVER+RestUrlConfig.EMPLOYEEPROJECTS)
	public EmployeeDto getemployee(@PathVariable String empId, @RequestParam Integer month,
			@RequestParam Integer year) {
		log.info("in employeecontroller projects");
		return employeeService.getEmployeeProjects(empId, month, year);
	}

	// Testing api to check server is up?

	@GetMapping("/health")
	public String getprojecthealth() {
		return "ALL OK";
	}
}
