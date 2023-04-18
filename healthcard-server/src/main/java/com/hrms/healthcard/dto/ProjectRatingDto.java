package com.hrms.healthcard.dto;

import java.util.List;

import lombok.Data;
@Data
public class ProjectRatingDto  {

	private String id;
	
	private Integer rating;
	
	private Integer ratingMonth;
	
	private Integer ratingYear;
	
	private String Comment;
	
	private List<ComponentRatingDto> ratings;
	
	private String status;
	
//	private Double averageRating;
	

	
	private  String projectName;
	
	private String projectManager;
	
	private String clientName;

	

	

	
}
