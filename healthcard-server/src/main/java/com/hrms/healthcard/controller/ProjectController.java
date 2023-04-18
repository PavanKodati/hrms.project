package com.hrms.healthcard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hrms.healthcard.Entity.Project;
import com.hrms.healthcard.dto.ProjectRatingWDto;
import com.hrms.healthcard.dto.responce;
import com.hrms.healthcard.service.ProjectService;
import com.hrms.healthcard.urlConfig.RestUrlConfig;

@RestController
public class ProjectController {
	@Autowired
	private ProjectService projectService;
	
	//To add all the components for the project

	@PutMapping(RestUrlConfig.HEALTHCARD_SERVER+RestUrlConfig.PROJECT+RestUrlConfig.PROJECTID+"/component")
	public Project addComponent(@PathVariable String projectId) {
		Project project = projectService.updateProjectAddComponent(projectId);
		return project;
	}

	
	//web get all projects:- 
	
	@GetMapping(RestUrlConfig.HEALTHCARD_SERVER+RestUrlConfig.ALLPROJECTS)
	public List<ProjectRatingWDto> getProjects(@RequestParam Integer month,@RequestParam Integer year) {
		List<ProjectRatingWDto> allProjects = projectService.getAllProjects(month,year);
		return allProjects;
	}

	
	//mobile project component or project rating
	
	@GetMapping(RestUrlConfig.HEALTHCARD_SERVER+RestUrlConfig.PROJECT+RestUrlConfig.PROJECTID)
	public responce getProject(@PathVariable String projectId,@RequestParam Integer month,@RequestParam Integer year) {
		 responce responce = projectService.getprojectComponent(projectId,month,year);
		return responce;
	}

}
