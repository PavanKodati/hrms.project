package com.example.demo.major.project.security;

import java.util.List;

import com.example.demo.major.project.domain.Building;

public class AdminLogIn {

	private String jwt;

	private List<Building> buildings;

	public String getJwt() {
		return jwt;
	}

	public void setJwt(String jwt) {
		this.jwt = jwt;
	}

	public List<Building> getBuildings() {
		return buildings;
	}

	public void setBuildings(List<Building> buildings) {
		this.buildings = buildings;
	}

	public AdminLogIn(String jwt, List<Building> buildings) {
		super();
		this.jwt = jwt;
		this.buildings = buildings;
	}

	public AdminLogIn() {
		super();
	}

	@Override
	public String toString() {
		return "AdminLogIn [jwt=" + jwt + ", buildings=" + buildings + "]";
	}

}
