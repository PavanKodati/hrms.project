package com.hrms.healthcard.dto;

import java.util.List;

import lombok.Data;

@Data
public class ProjectRatingWDto {

	private String id;

	private Integer ratingMonth;

	private Integer ratingYear;

	private String Comment;

	private List<ComponentRatingDto> ratings;

	private ProjectDto project;


}
