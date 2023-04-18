package com.hrms.healthcard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hrms.healthcard.Entity.ComponentRating;


public interface ComponentRatingRepository extends JpaRepository<ComponentRating, String> {
	
	@Query(value = "select * from component_rating where project_rating_id=?1",nativeQuery = true)
	List<ComponentRating> findByIdProjectRatingrId(String id);

	//List<ComponentRating> findbyprid(String prid);

	
	// select * from componenet_rating where project_rating_id = ''
}
