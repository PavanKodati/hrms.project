package com.hrms.healthcard.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.healthcard.Entity.Component;
import com.hrms.healthcard.Entity.ComponentRating;
import com.hrms.healthcard.Entity.Employee;
import com.hrms.healthcard.Entity.Project;
import com.hrms.healthcard.Entity.ProjectEmployees;
import com.hrms.healthcard.Entity.ProjectRating;
import com.hrms.healthcard.dto.ComponentRatingDto;
import com.hrms.healthcard.dto.ProjectRatingDto;
import com.hrms.healthcard.enums.RatingStatusEnum;
import com.hrms.healthcard.exception.ResourceNotFoundException;
import com.hrms.healthcard.repository.ComponentRatingRepository;
import com.hrms.healthcard.repository.ComponentRepository;
import com.hrms.healthcard.repository.ProjectEmployeesRepository;
import com.hrms.healthcard.repository.ProjectRatingRepository;
import com.hrms.healthcard.repository.ProjectRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProjectRatingService {

	@Autowired
	private ProjectRatingRepository projectRatingRepo;
	@Autowired
	private ComponentRatingRepository componentRatingRepo;

	@Autowired
	private ComponentRepository componentRepo;

	@Autowired
	private ProjectRepository projectRepo;

	@Autowired
	private ProjectEmployeesRepository projectEmployeesRepo;

	@Autowired
	private ModelMapper mapper;

	public ProjectRatingDto createRating(ProjectRatingDto projectRating, String pid) {

		log.info("project rating for month " + projectRating.getRatingMonth() + " and projectId " + pid);

		ProjectRating prating = projectRatingRepo.findByProjectid(pid, projectRating.getRatingMonth(),
				projectRating.getRatingYear());
		if (prating == null) {

			ProjectRating rating = mapper.map(projectRating, ProjectRating.class);

			Project pr = projectRepo.findById(pid).orElse(null);
			rating.setProject(pr);
			ProjectRating rate = projectRatingRepo.save(rating);

			List<ComponentRatingDto> ratings = projectRating.getRatings();
			for (ComponentRatingDto cr : ratings) {
				ComponentRating componentRating = mapper.map(cr, ComponentRating.class);
				componentRating.setProjectRating(rate);
				componentRatingRepo.save(componentRating);
			}
			return mapper.map(rate, ProjectRatingDto.class);
		} else {

			log.warn("Rating already submitted for this month " + projectRating.getRatingMonth() + " and projectId "
					+ pid);
			throw new ResourceNotFoundException("Rating already submitted for this month");
		}
	}

	@SuppressWarnings("null")
	public ProjectRatingDto getRating(String projectid, Integer month, Integer year) {

		log.info("WEB get individual rating for projectid " + projectid + " month " + month + " year " + year);

		Project p = projectRepo.findById(projectid)
				.orElseThrow(() -> new ResourceNotFoundException("No project Associated with projectID " + projectid));

		List<ProjectEmployees> projects = projectEmployeesRepo.findByProjectId(projectid);
		List<Employee> emps = new ArrayList<>();
		for (ProjectEmployees project : projects) {
			Employee employee = project.getEmployee();
			emps.add(employee);
		}

		ProjectRating projectRating = projectRatingRepo.findByProjectid(projectid, month, year);

		if (projectRating == null) {

			log.info("project rating is null");

			ProjectRating pr = new ProjectRating();
			List<Component> components = p.getComponent();

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

			pr.setComponentRatings(crs);
			ProjectRatingDto projectRatingDto = mapper.map(pr, ProjectRatingDto.class);
			for (Employee employees : emps) {
				if (employees.getRole().equalsIgnoreCase("PM")) {
					projectRatingDto.setProjectManager(employees.getName());
				}
			}
			projectRatingDto.setProjectName(p.getProjectName());
			projectRatingDto.setClientName(p.getClient().getClientName());
			projectRatingDto.setStatus(RatingStatusEnum.NOTSUBMITTED);

			return projectRatingDto;
		} else {
			ProjectRatingDto projectRatingDto = mapper.map(projectRating, ProjectRatingDto.class);

			List<ComponentRatingDto> list = projectRatingDto.getRatings();

			Collections.sort(list, new Comparator<ComponentRatingDto>() {

				@Override
				public int compare(ComponentRatingDto o1, ComponentRatingDto o2) {
					// TODO Auto-generated method stub
					return o1.getComponent().getId().compareTo(o2.getComponent().getId());
				}

			});

			for (Employee employees : emps) {
				if (employees.getRole().equalsIgnoreCase("PM")) {
					projectRatingDto.setProjectManager(employees.getName());
				}
			}
			projectRatingDto.setProjectName(p.getProjectName());
			projectRatingDto.setRatings(list);
			projectRatingDto.setClientName(p.getClient().getClientName());
			projectRatingDto.setStatus(RatingStatusEnum.SUBMITTED);

			return projectRatingDto;
		}

	}

	
}




//public ProjectRatingDto getAverageRating(String projectId, Integer month, Integer year) {
//	String mont = month.toString();
//	String yea = year.toString();
//
//	Project p = projectRepo.findById(projectId)
//			.orElseThrow(() -> new ResourceNotFoundException("No project Associated with projectID " + projectId));
//
//	List<ProjectEmployees> projects = projectEmployeesRepo.findByProjectId(projectId);
//	List<Employee> emps = new ArrayList<>();
//	for (ProjectEmployees project : projects) {
//		Employee employee = project.getEmployee();
//		emps.add(employee);
//	}
//
//	List<Integer> overallcrate = new ArrayList<>();
//	List<Integer> schedulecrate = new ArrayList<>();
//	List<Integer> resourcingcrate = new ArrayList<>();
//	List<Integer> Utilizationbudgetcrate = new ArrayList<>();
//	List<Integer> riskscrate = new ArrayList<>();
//	List<Integer> qualitycrate = new ArrayList<>();
//	List<Integer> scopecrate = new ArrayList<>();
//	List<Integer> changemgtcrate = new ArrayList<>();
//	List<Integer> issuescrate = new ArrayList<>();
//
//	// "02-2016"
//	String monthYear = null;
//	if (mont != "10" || mont != "11" || mont != "12") {
//		monthYear = "0" + mont + "-" + yea;
//		System.out.println(monthYear);
//	} else {
//		monthYear = mont + "-" + yea;
//		System.out.println(monthYear);
//	}
//
//	List<String> result = new ArrayList<>();
//	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-yyyy");
//	YearMonth yearMonth = YearMonth.parse(monthYear, formatter);
//
//	for (int i = 0; i < 12; i++) {
//		YearMonth prevMonth = yearMonth.minusMonths(i);
//		result.add(prevMonth.format(formatter));
//	}
//
//	for (String re : result) {
//
//		YearMonth yearMonths = YearMonth.parse(re, formatter);
//
//		ProjectRating projectRating = projectRatingRepo.findByProjectid(projectId, yearMonths.getMonthValue(),
//				yearMonths.getYear());
//		if (projectRating == null) {
//			System.out.println("ss");
//		}
//
//		List<ComponentRating> componentRatings = projectRating.getComponentRatings();
//
//		Collections.sort(componentRatings, new Comparator<ComponentRating>() {
//			@Override
//			public int compare(ComponentRating o1, ComponentRating o2) {
//				// TODO Auto-generated method stub
//				return o1.getComponent().getId().compareTo(o2.getComponent().getId());
//			}
//		});
//
//		for (ComponentRating cr : componentRatings) {
////			System.out.println(cr.getComponent().getComponentName());
//			if (cr.getComponent().getComponentName().equalsIgnoreCase("overall")) {
//				Integer componentRating = cr.getComponentRating();
//				overallcrate.add(componentRating);
//			}
//
//			if (cr.getComponent().getComponentName().equalsIgnoreCase("schedule")) {
//
//				int crating = cr.getComponentRating();
//				schedulecrate.add(crating);
//
//			}
//			if (cr.getComponent().getComponentName().equalsIgnoreCase("resourcing")) {
//				int crating = cr.getComponentRating();
//				resourcingcrate.add(crating);
//			}
//			if (cr.getComponent().getComponentName().equalsIgnoreCase("Utilization/budget")) {
//				int crating = cr.getComponentRating();
//				Utilizationbudgetcrate.add(crating);
//			}
//			if (cr.getComponent().getComponentName().equalsIgnoreCase("risks")) {
//				int crating = cr.getComponentRating();
//				riskscrate.add(crating);
//			}
//			if (cr.getComponent().getComponentName().equalsIgnoreCase("quality")) {
//				int crating = cr.getComponentRating();
//				qualitycrate.add(crating);
//			}
//			if (cr.getComponent().getComponentName().equalsIgnoreCase("scope")) {
//				int crating = cr.getComponentRating();
//				scopecrate.add(crating);
//			}
//			if (cr.getComponent().getComponentName().equalsIgnoreCase("changemgt")) {
//				int crating = cr.getComponentRating();
//				changemgtcrate.add(crating);
//			}
//			if (cr.getComponent().getComponentName().equalsIgnoreCase("issues")) {
//				int crating = cr.getComponentRating();
//				issuescrate.add(crating);
//			}
//
//		}
//	}
//
//	List<Double> averageRatings = new ArrayList<>();
//
//	Double overall = getaverage(overallcrate);
//	averageRatings.add(overall);
//	System.out.println(overall);
//
//	Double schedule = getaverage(schedulecrate);
//	averageRatings.add(schedule);
//	System.out.println(schedule);
//
//	Double resourcing = getaverage(resourcingcrate);
//	averageRatings.add(resourcing);
//	System.out.println(resourcing);
//
//	Double Utilization = getaverage(Utilizationbudgetcrate);
//	averageRatings.add(Utilization);
//	System.out.println(Utilization);
//
//	Double risks = getaverage(riskscrate);
//	averageRatings.add(risks);
//	System.out.println(risks);
//
//	Double quality = getaverage(qualitycrate);
//	averageRatings.add(quality);
//	System.out.println(quality);
//
//	Double scope = getaverage(scopecrate);
//	averageRatings.add(scope);
//	System.out.println(scope);
//
//	Double changemgmt = getaverage(changemgtcrate);
//	averageRatings.add(changemgmt);
//	System.out.println(changemgmt);
//
//	Double issues = getaverage(issuescrate);
//	averageRatings.add(issues);
//	System.out.println(issues);
//
//	List<Component> components = p.getComponent();
//
//	Collections.sort(components, new Comparator<Component>() {
//		@Override
//		public int compare(Component o1, Component o2) {
//			// TODO Auto-generated method stub
//			return o1.getId().compareTo(o2.getId());
//		}
//	});
//
//	ProjectRating prdt = new ProjectRating();
//	List<ComponentRating> crs = new ArrayList<>();
//	for (Component c : components) {
//		ComponentRating cr = new ComponentRating();
//		Component comp = componentRepo.findById(c.getId()).orElse(null);
//		cr.setComponent(comp);
//		cr.setComponentRating(null);
//		cr.setId(null);
//		cr.setNotes(null);
//		crs.add(cr);
//	}
//	prdt.setComponentRatings(crs);
//	ProjectRatingDto projectRatingDto = mapper.map(prdt, ProjectRatingDto.class);
//	projectRatingDto.setClientName(p.getClient().getClientName());
//	projectRatingDto.setProjectName(p.getProjectName());
//	List<ComponentRatingDto> cratings = projectRatingDto.getRatings();
//	
//	for (ComponentRatingDto cr : cratings) {
//					
//			if (cr.getComponent().getComponentName().equalsIgnoreCase("overall")) {
//				cr.setAverageRating(overall);
//			}
//
//			if (cr.getComponent().getComponentName().equalsIgnoreCase("schedule")) {
//
//				cr.setAverageRating(schedule);
//
//			}
//			if (cr.getComponent().getComponentName().equalsIgnoreCase("resourcing")) {
//				cr.setAverageRating(resourcing);
//			}
//			if (cr.getComponent().getComponentName().equalsIgnoreCase("Utilization/budget")) {
//				cr.setAverageRating(Utilization);
//			}
//			if (cr.getComponent().getComponentName().equalsIgnoreCase("risks")) {
//				cr.setAverageRating(risks);
//			}
//			if (cr.getComponent().getComponentName().equalsIgnoreCase("quality")) {
//				cr.setAverageRating(quality);
//			}
//			if (cr.getComponent().getComponentName().equalsIgnoreCase("scope")) {
//				cr.setAverageRating(scope);
//			}
//			if (cr.getComponent().getComponentName().equalsIgnoreCase("changemgt")) {
//				cr.setAverageRating(changemgmt);
//			}
//			if (cr.getComponent().getComponentName().equalsIgnoreCase("issues")) {
//				cr.setAverageRating(issues);
//			}
//		
//		}
//	
//	
//
//	for (Employee employees : emps) {
//		if (employees.getRole().equalsIgnoreCase("PM")) {
//			projectRatingDto.setProjectManager(employees.getName());
//		}
//
//	}
//	return projectRatingDto;
//
//}
//
//public Double getaverage(List<Integer> list) {
//	Double sums = (double) 0;
//	for (Integer value : list) {
//		sums += value;
//	}
//	Double su = (Double) (sums / 12);
//	return su;
//}

