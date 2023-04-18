package com.example.demo.major.project.DTO;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.example.demo.major.project.domain.Building;
import com.example.demo.major.project.domain.User;
@Table(name = "user", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class UserDTO {

	private Integer id;
	
	@NotBlank
	private String name;

	@Email
	private String email;

	@NotBlank
	@Pattern(regexp = "^(?=.*\\d)(?=.*[A-Za-z])[A-Za-z0-9]{8,20}$", message = "Passwords should have at least 8 characters and alphanumeric")
	private String password;

	@NotBlank
	private String role;

	@NotBlank
	private String city;

	@OneToMany(targetEntity = Building.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "userId", referencedColumnName = "id")
	private List<Building> buildings;

	public List<Building> getBuildings() {
		return buildings;
	}

	public void setBuildings(List<Building> buildings) {
		this.buildings = buildings;
	}

	public UserDTO() {
		super();
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", role=" + role
				+ ", city=" + city + "]";
	}

	public UserDTO(Integer id, @NotBlank String name, @Email String email,
			@NotBlank @Pattern(regexp = "^(?=.*\\d)(?=.*[A-Za-z])[A-Za-z0-9]{8,20}$", message = "Passwords should have at least 8 characters and alphanumeric") String password,
			@NotBlank String role, @NotBlank String city, List<Building> buildings) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.role = role;
		this.city = city;
		this.buildings = buildings;
	}

	public UserDTO(User user) {
		this.id = user.getId();
		this.name = user.getName();
		this.email = user.getEmail();
		this.password = user.getPassword();
		this.role = user.getRole();
		this.city = user.getCity();
		this.buildings = user.getBuildings();
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
