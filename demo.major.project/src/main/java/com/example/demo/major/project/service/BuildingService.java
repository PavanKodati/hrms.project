package com.example.demo.major.project.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.demo.major.project.DTO.BuildingDTO;
import com.example.demo.major.project.domain.Building;
import com.example.demo.major.project.domain.User;
import com.example.demo.major.project.exception.DuplicateEntryException;
import com.example.demo.major.project.exception.ResourceNotFoundException;
import com.example.demo.major.project.repository.AuthenticationRepository;
import com.example.demo.major.project.repository.BuildingRepository;

@Service
public class BuildingService {

	@Autowired
	private AuthenticationRepository authenticationRepository;
	@Autowired
	private BuildingRepository buildingRepository;

	public int idheader() {
		UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) SecurityContextHolder
				.getContext().getAuthentication();
		Integer userId = (Integer) token.getPrincipal();
		return userId;
	}

	public String getRoleHeader() {
		UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) SecurityContextHolder
				.getContext().getAuthentication();
		Collection<GrantedAuthority> role = token.getAuthorities();
		for (GrantedAuthority r : role) {
			if (r.getAuthority().equals("admin"))
				return "admin";
		}
		return "user";
	}

	public List<BuildingDTO> getallbuildings() {
		List<BuildingDTO> bld = new ArrayList<BuildingDTO>();
		if (getRoleHeader().equals("admin")) {
			User user = authenticationRepository.getById(idheader());

			if (user.getBuildings().isEmpty()) {
				throw new ResourceNotFoundException("No Buildings Associated to user");
			} else {

				List<Building> buildings = user.getBuildings();
				for (Building bd : buildings) {
					bld.add(new BuildingDTO(bd.getId(), bd.getBuildingNumber(), bd.getBuildingName(), bd.getArea(),
							bd.getTown(), bd.getState(), bd.getLandmark(), bd.getPincode(), bd.getSlots()));
				}
				bld.sort((a1, a2) -> a1.getBuildingName().compareTo(a2.getBuildingName()));
				return bld;
			}

		} else {
			if (buildingRepository.findAll().isEmpty()) {
				throw new ResourceNotFoundException("No buildings Available");
			} else {
				List<Building> buildings = buildingRepository.findAll();
				for (Building bd : buildings) {
					bld.add(new BuildingDTO(bd.getId(), bd.getBuildingNumber(), bd.getBuildingName(), bd.getArea(),
							bd.getTown(), bd.getState(), bd.getLandmark(), bd.getPincode(), bd.getSlots()));
				}
				bld.sort((a1, a2) -> a1.getBuildingName().compareTo(a2.getBuildingName()));
				return bld;
			}
		}
	}

	public void addBuilding(BuildingDTO build) {
		Building bld = new Building();
		Integer userId = idheader();
		User us = authenticationRepository.findById(userId).orElse(null);
		if (us != null) {

			List<Building> buildings = buildingRepository.findAll();
			Iterator<Building> itrb = buildings.iterator();
			while (itrb.hasNext()) {
				Building blds = itrb.next();
				if (blds.getBuildingNumber().equals(build.getBuildingNumber())) {
					throw new DuplicateEntryException("building number cannot be duplicate");
				}
			}
			bld.setBuildingName(build.getBuildingName());
			bld.setArea(build.getArea());
			bld.setBuildingNumber(build.getBuildingNumber());
			bld.setLandmark(build.getLandmark());
			bld.setPincode(build.getPincode());
			bld.setState(build.getState());
			bld.setTown(build.getTown());
			bld.setSlots(build.getSlots());
			us.getBuildings().add(bld);
			authenticationRepository.save(us);
		}
	}

	public String deleteBuilding(String buildingNumber) {

		User us = authenticationRepository.findById(idheader()).orElse(null);
		List<Building> li = us.getBuildings();
		Iterator<Building> itr = li.iterator();

		while (itr.hasNext()) {
			Building bld = itr.next();
			if (bld.getBuildingNumber().equals(buildingNumber)) {

				li.remove(bld);
				authenticationRepository.save(us);
				buildingRepository.delete(bld);
				return "Building deleted with building number " + buildingNumber;
			}
		}
		throw new ResourceNotFoundException("No Building Found with Building Number " + buildingNumber);

	}

	public String updateBuildings(String buildingNumber, BuildingDTO build) {

		Integer userId = idheader();

		User us = authenticationRepository.findById(userId).orElse(null);

		List<Building> lb = us.getBuildings();
		Building building = null;
		Iterator<Building> itr = lb.iterator();
		while (itr.hasNext()) {

			building = itr.next();
			if (building.getBuildingNumber().equals(buildingNumber)) {

				if (build.getArea() != null) {
					building.setArea(build.getArea());
				}
				if (build.getBuildingName() != null) {
					building.setBuildingName(build.getBuildingName());
				}
				if (build.getLandmark() != null) {
					building.setLandmark(build.getLandmark());
				}
				if (build.getPincode() != null) {
					building.setPincode(build.getPincode());
				}
				if (build.getState() != null) {
					building.setState(build.getState());
				}
				if (build.getTown() != null) {
					building.setTown(build.getTown());
				}
				authenticationRepository.save(us);
				return "Sucessfully updated";
			}

		}
		throw new ResourceNotFoundException("Invalid Building Number");

	}

	public List<BuildingDTO> getByPinCodeBuildings(Integer pin) {
		ArrayList<BuildingDTO> listofbuildings = new ArrayList<BuildingDTO>();

		if (getRoleHeader().equals("admin")) {
			User us = authenticationRepository.findById(idheader()).orElse(null);
			List<Building> lb = us.getBuildings();
			Iterator<Building> itr = lb.iterator();
			while (itr.hasNext()) {
				Building bd = itr.next();

				if (bd.getPincode().equals(pin)) {

					listofbuildings
							.add(new BuildingDTO(bd.getId(), bd.getBuildingNumber(), bd.getBuildingName(), bd.getArea(),
									bd.getTown(), bd.getState(), bd.getLandmark(), bd.getPincode(), bd.getSlots()));
				}
			}
			if (listofbuildings.isEmpty()) {
				throw new ResourceNotFoundException("No Buildings present with this Pincode");
			} else

				listofbuildings.sort((a1, a2) -> a1.getBuildingName().compareTo(a2.getBuildingName()));
			return listofbuildings;

		} else {
			List<Building> listb = buildingRepository.findAll();

			Iterator<Building> itr = listb.iterator();
			while (itr.hasNext()) {

				Building bd = itr.next();

				if (bd.getPincode().equals(pin)) {

					listofbuildings
							.add(new BuildingDTO(bd.getId(), bd.getBuildingNumber(), bd.getBuildingName(), bd.getArea(),
									bd.getTown(), bd.getState(), bd.getLandmark(), bd.getPincode(), bd.getSlots()));
				}
			}
			if (listofbuildings.isEmpty()) {
				throw new ResourceNotFoundException("No Buildings present with this Pincode");
			} else
				listofbuildings.sort((a1, a2) -> a1.getBuildingName().compareTo(a2.getBuildingName()));
			return listofbuildings;
		}
	}

	public List<BuildingDTO> getByNameBuildings(String name) {

		ArrayList<BuildingDTO> listofbuildings = new ArrayList<BuildingDTO>();

		if (getRoleHeader().equals("admin")) {
			User us = authenticationRepository.findById(idheader()).orElse(null);
			List<Building> lb = us.getBuildings();

			Iterator<Building> itr = lb.iterator();
			while (itr.hasNext()) {
				Building bd = itr.next();
				if (bd.getBuildingName().equals(name)) {
					listofbuildings
							.add(new BuildingDTO(bd.getId(), bd.getBuildingNumber(), bd.getBuildingName(), bd.getArea(),
									bd.getTown(), bd.getState(), bd.getLandmark(), bd.getPincode(), bd.getSlots()));
				}
			}
			if (listofbuildings.isEmpty()) {
				throw new ResourceNotFoundException("No Buildings present with this BuildingName");
			} else {
				listofbuildings.sort((a1, a2) -> a1.getBuildingName().compareTo(a2.getBuildingName()));
				return listofbuildings;
			}
		} else {
			List<Building> listB = buildingRepository.findAll();
			Iterator<Building> itr = listB.iterator();
			while (itr.hasNext()) {
				Building bd = itr.next();
				if (bd.getBuildingName().equals(name)) {
					listofbuildings
							.add(new BuildingDTO(bd.getId(), bd.getBuildingNumber(), bd.getBuildingName(), bd.getArea(),
									bd.getTown(), bd.getState(), bd.getLandmark(), bd.getPincode(), bd.getSlots()));
				}
			}
			if (listofbuildings.isEmpty()) {
				throw new ResourceNotFoundException("No Buildings present with this BuildingName");
			} else {
				listofbuildings.sort((a1, a2) -> a1.getBuildingName().compareTo(a2.getBuildingName()));
				return listofbuildings;
			}

		}

	}
}
