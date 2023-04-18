package com.hrms.healthcard.dto;

import java.util.Date;
import java.util.List;

import com.hrms.healthcard.Entity.Component;

import lombok.Data;
@Data
public class ProjectDto {
	
    private String id;
	
	private String projectName;
	
	private String description;
	
	private Date startDate;
	
	private Date endDate;
	
	private String status;
	
	private String logo;
	
	private Integer overallRating;
	
	private List<ComponentDto> component;
	
	
	

}
