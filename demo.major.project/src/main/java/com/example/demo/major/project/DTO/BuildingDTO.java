package com.example.demo.major.project.DTO;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.example.demo.major.project.domain.Slot;

public class BuildingDTO {
	
	private Integer id;
	
	@NotBlank(message = "buildingNumber cannot be empty")
	private String buildingNumber;

	@Length(min = 8, max = 20, message = "length should be alteast 8")
	private String buildingName;

	@NotBlank(message = "area cannot be empty")
	private String area;

	@NotBlank(message = "town cannnot be empty")
	private String town;

	@NotBlank(message = "message cannot be empty")
	private String state;

	@NotBlank(message = "landmark cannot be empty")
	private String landmark;

	@NotNull(message = "enter valid pincode,pincode cannot be null")
	private Integer pincode;

	
	private List<Slot> slots;

	public List<Slot> getSlots() {
		return slots;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setSlots(List<Slot> slots) {
		this.slots = slots;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public String getBuildingNumber() {
		return buildingNumber;
	}

	public void setBuildingNumber(String buildingNumber) {
		this.buildingNumber = buildingNumber;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getLandmark() {
		return landmark;
	}

	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}

	public Integer getPincode() {
		return pincode;
	}

	public void setPincode(Integer pincode) {
		this.pincode = pincode;
	}

	@Override
	public String toString() {
		return "Building [id=" + id + ", buildingNumber=" + buildingNumber + ", buildingName=" + buildingName
				+ ", area=" + area + ", town=" + town + ", state=" + state + ", landmark=" + landmark + ", pincode="
				+ pincode + "]";
	}

	public BuildingDTO(Integer id, @NotBlank(message = "buildingNumber cannot be empty") String buildingNumber,
			@Length(min = 8, max = 20, message = "length should be alteast 8") String buildingName,
			@NotBlank(message = "area cannot be empty") String area,
			@NotBlank(message = "town cannnot be empty") String town,
			@NotBlank(message = "message cannot be empty") String state,
			@NotBlank(message = "landmark cannot be empty") String landmark,
			@NotNull(message = "enter valid pincode,pincode cannot be null") Integer pincode, List<Slot> slots) {
		super();
		this.id = id;
		this.buildingNumber = buildingNumber;
		this.buildingName = buildingName;
		this.area = area;
		this.town = town;
		this.state = state;
		this.landmark = landmark;
		this.pincode = pincode;
		this.slots = slots;
	}

	public BuildingDTO() {
		super();
	}

}
