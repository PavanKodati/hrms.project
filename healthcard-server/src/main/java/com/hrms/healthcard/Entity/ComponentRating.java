package com.hrms.healthcard.Entity;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class ComponentRating {
	
	@Id
	@GeneratedValue(generator="uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	
	private Integer componentRating;
	
	private String notes;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="componentId")
	private Component component;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="project_rating_id")
	private ProjectRating projectRating;
	

}
