package com.hrms.healthcard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hrms.healthcard.Entity.ProjectRating;

public interface ProjectRatingRepository extends JpaRepository<ProjectRating, String> {
	
	
	@Query(value = "select * from project_healthcard.project_rating  where project_id=?1 and rating_month=?2 and rating_year=?3",nativeQuery = true)
	ProjectRating findByProjectid(String projectid,Integer month,Integer year);

	
	

	
}
