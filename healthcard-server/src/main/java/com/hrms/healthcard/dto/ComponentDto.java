package com.hrms.healthcard.dto;

import lombok.Data;

@Data
public class ComponentDto {

	private String id;

	private String componentName;

	private String status;
	
	private String questionType;
	
	private String componentQuestion;
}
