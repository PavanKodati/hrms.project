package com.hrms.healthcard.service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.healthcard.Entity.Component;
import com.hrms.healthcard.Entity.ComponentRating;
import com.hrms.healthcard.Entity.Project;
import com.hrms.healthcard.Entity.ProjectRating;
import com.hrms.healthcard.dto.ComponentRatingDto;
import com.hrms.healthcard.dto.ProjectDto;
import com.hrms.healthcard.dto.ProjectRatingDto;
import com.hrms.healthcard.dto.ProjectRatingWDto;
import com.hrms.healthcard.dto.responce;
import com.hrms.healthcard.repository.ComponentRepository;
import com.hrms.healthcard.repository.ProjectRatingRepository;
import com.hrms.healthcard.repository.ProjectRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProjectService {
	@Autowired
	private ModelMapper modelmapper;
	@Autowired
	private ProjectRatingRepository projectRatingRepo;

	@Autowired
	private ProjectRepository projectRepo;
	@Autowired
	private ComponentRepository componentRepo;

	public Project updateProjectAddComponent(String projectId) {
		log.info("components added to this" + projectId);
		Project project = projectRepo.findById(projectId).orElse(null);
		List<Component> all = componentRepo.findAll();
		project.setComponent(all);
		projectRepo.save(project);
		return project;
	}

	public responce getprojectComponent(String projectId, Integer month, Integer year) {
		log.info("projectId " + projectId + "month " + month + "year " + year);
		ProjectRating rating = projectRatingRepo.findByProjectid(projectId, month, year);

		if (rating != null) {
			ProjectRatingDto projectRating = modelmapper.map(rating, ProjectRatingDto.class);
			log.info("rating for month " + month + " and projectId " + projectId);

			List<ComponentRatingDto> list = projectRating.getRatings();

			Collections.sort(list, new Comparator<ComponentRatingDto>() {
				@Override
				public int compare(ComponentRatingDto o1, ComponentRatingDto o2) {
					// TODO Auto-generated method stub
					return o1.getComponent().getId().compareTo(o2.getComponent().getId());
				}
			});

			projectRating.setRatings(list);
			return new responce(projectRating);
		} else {
			Project project = projectRepo.findById(projectId).orElse(null);

			log.info("list of project components");
			ProjectDto projectDto = modelmapper.map(project, ProjectDto.class);
			return new responce(projectDto);
		}
	}

	public List<ProjectRatingWDto> getAllProjects(Integer month, Integer year) {

		log.info("month" + month + "year" + year);
		List<Project> projects = projectRepo.findAll();

		List<Project> projectss = new ArrayList<>();

		for (Project prs : projects) {
			
			Date endDate = prs.getEndDate();
			LocalDate localDate = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				
			YearMonth untilYearMonth = YearMonth.from(localDate);
			

			YearMonth inputYearMonth = YearMonth.of(year, month);
			
			System.out.println("local date :"+untilYearMonth+" and "+" input month and year "+inputYearMonth);
//			int endYear = calendar.get(Calendar.YEAR);
//			int endMonth = calendar.get(Calendar.MONTH) + 1; // month is 0-based, so add 1
//
//			System.out.println(" month " + endMonth + " end year " + endYear + " project id " + prs.getId());
//			
//month <= endMonth &&  year <= endYear 
			if (!inputYearMonth.isAfter(untilYearMonth)  && prs.getStatus().equalsIgnoreCase("inactive")) {

				projectss.add(prs);
			}
			if (prs.getStatus().equalsIgnoreCase("active")) {
				projectss.add(prs);
			}
		}

		List<ProjectRatingWDto> prdtos = new ArrayList<>();

		for (Project pr : projectss) {
			log.info(pr.getId());
			System.out.println(pr.getId());

			Project project = projectRepo.findById(pr.getId()).orElse(null);

			ProjectRating projectRating = projectRatingRepo.findByProjectid(pr.getId(), month, year);
			if (projectRating == null) {
				ProjectRatingWDto pdw = new ProjectRatingWDto();
				pdw.setComment(null);
				pdw.setId(null);
				pdw.setRatingMonth(month);

				List<Component> components = project.getComponent();

				List<ComponentRating> crs = new ArrayList<>();

				for (Component c : components) {
					ComponentRating cr = new ComponentRating();
					Component comp = componentRepo.findById(c.getId()).orElse(null);
					cr.setComponent(comp);
					cr.setComponentRating(null);
					cr.setId(null);
					cr.setNotes(null);
					crs.add(cr);
				}
				List<ComponentRatingDto> list = crs.stream().map(cr -> modelmapper.map(cr, ComponentRatingDto.class))
						.collect(Collectors.toList());
				pdw.setRatings(list);
				ProjectDto projectDto = modelmapper.map(project, ProjectDto.class);
				pdw.setProject(projectDto);
				pdw.setRatingYear(year);
				prdtos.add(pdw);
			} else {
				ProjectRatingWDto projectRatingDto = modelmapper.map(projectRating, ProjectRatingWDto.class);
				List<ComponentRatingDto> list = projectRatingDto.getRatings();

				Collections.sort(list, new Comparator<ComponentRatingDto>() {

					@Override
					public int compare(ComponentRatingDto o1, ComponentRatingDto o2) {
						// TODO Auto-generated method stub
						return o1.getComponent().getId().compareTo(o2.getComponent().getId());
					}

				});
				ProjectDto projectDto = modelmapper.map(project, ProjectDto.class);
				projectRatingDto.setProject(projectDto);
				prdtos.add(projectRatingDto);
			}
		}
		return prdtos;
	}
}
