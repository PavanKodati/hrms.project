package com.hrms.healthcard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hrms.healthcard.dto.ProjectRatingDto;
import com.hrms.healthcard.service.ProjectRatingService;
import com.hrms.healthcard.urlConfig.RestUrlConfig;

@RestController
public class ProjectRatingController {

	@Autowired
	private ProjectRatingService projectRatingService;

	
	
	// mobile post project rating

	@PostMapping(RestUrlConfig.HEALTHCARD_SERVER+RestUrlConfig.PROJECT + RestUrlConfig.PROJECTID + RestUrlConfig.PROJECTRATING)
	public ProjectRatingDto rateProject(@RequestBody ProjectRatingDto projectRating, @PathVariable String projectId) {
		return projectRatingService.createRating(projectRating, projectId);
	}

	
	
	// web individual project rating

	@GetMapping(RestUrlConfig.HEALTHCARD_SERVER+RestUrlConfig.PROJECT + RestUrlConfig.PROJECTID + RestUrlConfig.PROJECTRATING)
	public ProjectRatingDto webGetRating(@PathVariable String projectId, @RequestParam Integer month,
			@RequestParam Integer year) {
		return projectRatingService.getRating(projectId, month, year);
	}
	
	
	
//	//date range Average
//	@GetMapping("/projectrating/average/{projectId}")
//	ProjectRatingDto webDateRangeGetRating(@PathVariable String projectId, @RequestParam Integer month,
//			@RequestParam Integer year) {
//		
//		return projectRatingService.getAverageRating(projectId, month, year);
//		
//		
//		
//	}
	
	
	
	
}
