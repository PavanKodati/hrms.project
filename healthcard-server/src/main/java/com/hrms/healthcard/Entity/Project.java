package com.hrms.healthcard.Entity;

import java.util.Date;
import java.util.List;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "project")
public class Project {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

	private String projectName;
	
	private String description;

	private Date startDate;

	private Date endDate;

	private String status;

	private String logo;


	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "project_component", joinColumns = @JoinColumn(name = "project_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "component_id", referencedColumnName = "id"))
	List<Component> component;

	@JsonIgnore
	@OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
	private List<ProjectRating> projectRating;
	
	@ManyToOne
	@JsonIgnore
	private Client client;
	
	

}
