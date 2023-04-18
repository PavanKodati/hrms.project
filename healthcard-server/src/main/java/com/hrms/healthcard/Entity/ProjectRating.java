package com.hrms.healthcard.Entity;

import java.util.List;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class ProjectRating {
	@Id
	@GeneratedValue(generator="uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	
	private Integer rating;
	
	private Integer ratingMonth;
	
	private Integer ratingYear;
	
	private String Comment;
	
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "projectRating")
	private List<ComponentRating> componentRatings;
	
	@ManyToOne
	@JoinColumn(name = "project_id", nullable = false)
	private Project project;

}
