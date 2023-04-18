package com.hrms.healthcard.dto;

import lombok.Data;

@Data
public class responce {

	private ProjectDto project;
	
	private ProjectRatingDto projectRating;

	public responce(ProjectDto project) {
		super();
		this.project = project;
	}

	public responce(ProjectRatingDto projectRating) {
		super();
		this.projectRating = projectRating;
	}

	
	
	
	
}
