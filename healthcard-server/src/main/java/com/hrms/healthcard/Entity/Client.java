package com.hrms.healthcard.Entity;

import java.util.List;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Client {
	@Id
	@GeneratedValue(generator="uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

	private String clientName;
	
	private String status;
	
	private Integer customerValue;
	
	
	@OneToMany(targetEntity = Project.class)
	@JoinColumn(name = "client_id",referencedColumnName = "id")
	private List<Project> projects;
}
