package com.hrms.healthcard.Entity;

import java.util.List;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Component {
	@Id
	@GeneratedValue(generator="uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	
	private String componentName;
	
	private String status;
	
	private String questionType;
	
	private String componentQuestion;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "component")
	private List<ComponentRating> componentRatings;
}
