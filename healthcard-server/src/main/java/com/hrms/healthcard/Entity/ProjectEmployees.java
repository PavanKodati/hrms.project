package com.hrms.healthcard.Entity;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class ProjectEmployees {
	
	   @Id
	   @GeneratedValue(generator="uuid")
	   @GenericGenerator(name = "uuid", strategy = "uuid2")
	   private String id;
	   
	   
	   @ManyToOne
	   @JoinColumn(name="employee_id")
	   private Employee employee;
	   
	   
	   @ManyToOne
	   @JoinColumn(name="project_id")
	   private Project project;
	   
	   private String role;

	
}
