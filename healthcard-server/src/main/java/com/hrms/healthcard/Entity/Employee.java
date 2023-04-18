package com.hrms.healthcard.Entity;

import java.util.List;

import org.hibernate.annotations.GenericGenerator;

import com.hrms.healthcard.dto.EmployeeDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;


@Entity
@Data
@Table(name = "employee")

public class Employee {
	@Id
	@GeneratedValue(generator="uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	
	private String email;
	
	private String name;

	private String status;
	
	private String role;
	
	@Column(nullable = false, unique = true)
	//@Size(max = 4, message = "FlId should be max 4 number")
	private String flId;
	
//	@JsonIgnore
	@OneToMany(mappedBy = "employee")
	private List<ProjectEmployees> projectEmployees;

	
	
	
}

