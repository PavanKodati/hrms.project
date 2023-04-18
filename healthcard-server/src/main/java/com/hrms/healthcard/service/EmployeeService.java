package com.hrms.healthcard.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.healthcard.Entity.ComponentRating;
import com.hrms.healthcard.Entity.Employee;
import com.hrms.healthcard.Entity.Project;
import com.hrms.healthcard.Entity.ProjectEmployees;
import com.hrms.healthcard.Entity.ProjectRating;
import com.hrms.healthcard.dto.EmployeeDto;
import com.hrms.healthcard.dto.EmployeeMDto;
import com.hrms.healthcard.dto.ProjectDto;
import com.hrms.healthcard.exception.ResourceNotFoundException;
import com.hrms.healthcard.repository.EmployeeRepository;
import com.hrms.healthcard.repository.ProjectRatingRepository;
import com.hrms.healthcard.security.TokenService;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepo;
	@Autowired
	private ProjectRatingRepository projectRatingRepository;

	@Autowired
	TokenService tokenService;

	@Autowired
	private ModelMapper mapper;

	public EmployeeDto getEmployeeProjects(String empId, Integer month, Integer year) {

		log.info("projects of empid " + empId);

		Employee emp = employeeRepo.findById(empId)
				.orElseThrow(() -> new ResourceNotFoundException("employee not found with id : " + empId));
		List<ProjectEmployees> projectEmployees = emp.getProjectEmployees();
		
		List<Project> projects = new ArrayList<Project>();
		for (ProjectEmployees projectEmployees2 : projectEmployees) {
		Project project = projectEmployees2.getProject();
		if(project.getStatus().equalsIgnoreCase("active")) {
		projects.add(project);
		}
	}

		Integer componentRatin = null;

		List<ProjectDto> pdtos = new ArrayList<>();
		for (Project pr : projects) {

			ProjectRating rating = projectRatingRepository.findByProjectid(pr.getId(), month, year);
			if (rating != null) {
				List<ComponentRating> componentRatings = rating.getComponentRatings();

				for (ComponentRating componentRating : componentRatings) {
					if (componentRating.getComponent().getComponentName().equalsIgnoreCase("overall")) {

						log.info("component rating for the project " + pr.getId() + " component "
								+ componentRating.getComponent().getId() + " and component name "
								+ componentRating.getComponent().getComponentName() + " and rating is "
								+ componentRating.getComponentRating());

						componentRatin = componentRating.getComponentRating();

					}
				}
			} else {
				componentRatin = null;
			}
			ProjectDto projectDto = mapper.map(pr, ProjectDto.class);
			projectDto.setComponent(null);
			projectDto.setOverallRating(componentRatin);
			pdtos.add(projectDto);
		}
		EmployeeDto employee = mapper.map(emp, EmployeeDto.class);
		employee.setProjects(pdtos);
		return employee;
	}

	public EmployeeMDto getEmployee(HttpServletRequest request) {
		final String authorizationHeader = request.getHeader("Authorization");
		String token = authorizationHeader.substring(7);

		Boolean tokenExpired = tokenService.isTokenExpired(token);
		log.info(token);

		if (tokenExpired == true) {
			throw new ResourceNotFoundException("token expired");
		}

		Claims claim = tokenService.getAllClaims(token);
		String id = claim.get("id").toString();

		Employee emp = employeeRepo.findById(id).orElse(null);

		if (emp == null) {
			throw new ResourceNotFoundException("employee details not found");
		}

		EmployeeMDto employeeMDto = mapper.map(emp, EmployeeMDto.class);
		return employeeMDto;
	}
}
