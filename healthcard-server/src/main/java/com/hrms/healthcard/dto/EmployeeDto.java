package com.hrms.healthcard.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;
@Data
public class EmployeeDto {
	
	private String id;
	
	private String email;
	
	private String name;

	private String status;
	
	private String flId;
	
	private String role;
	
	private List<ProjectDto> projects;

}
