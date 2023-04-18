package com.hrms.healthcard.dto;

import lombok.Data;

@Data
public class ComponentRatingDto {

	private String id;

	private Integer componentRating;
	
	private Double averageRating;
	

	private String notes;

	private ComponentDto component;

}
