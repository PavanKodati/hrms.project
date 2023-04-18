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

import com.example.demo.major.project.DTO.SlotDTO;
import com.example.demo.major.project.domain.Building;
import com.example.demo.major.project.domain.Slot;
import com.example.demo.major.project.domain.User;
import com.example.demo.major.project.exception.ResourceNotFoundException;
import com.example.demo.major.project.repository.AuthenticationRepository;
import com.example.demo.major.project.repository.BuildingRepository;
import com.example.demo.major.project.repository.SlotRepository;

@Service
public class SlotService {

	@Autowired
	private AuthenticationRepository authenticationRepository;
	@Autowired
	private BuildingRepository buildingRepository;
	@Autowired
	private SlotRepository slotRepository;

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

	public String addSlot(String buildingNumber, SlotDTO slot) {
		Slot slots=new Slot();
		
		User us = authenticationRepository.findById(idheader()).orElse(null);
		List<Building> li = us.getBuildings();
		Iterator<Building> itr = li.iterator();
		Building bld = null;
		while (itr.hasNext()) {
			bld = itr.next();
			if (bld.getBuildingNumber().equals(buildingNumber)) {

				List<Slot> sl = bld.getSlots();
				slots.setSlotNumber(slot.getSlotNumber());
				slots.setAvaliability(slot.getAvaliability());
				sl.add(slots);
				slotRepository.saveAll(sl);
				buildingRepository.save(bld);
				return "Slot added to building " + buildingNumber;
			}
		}
		throw new ResourceNotFoundException("No Building is present with building Number " + buildingNumber);
	}

	public List<SlotDTO> getAllSlots(String buildingNumber) {
		List<SlotDTO> lists=new ArrayList<SlotDTO>();
		
		User us = authenticationRepository.findById(idheader()).orElse(null);
		List<Building> listofBuildings = us.getBuildings();
		Iterator<Building> itr = listofBuildings.iterator();
		Building bld = null;
		while (itr.hasNext()) {
			bld = itr.next();
			if (bld.getBuildingNumber().equals(buildingNumber)) {
				
				List<Slot> slts= bld.getSlots();
				for(Slot s:slts) {
					lists.add(new SlotDTO(s.getId(), s.getSlotNumber(), s.getFloornumber(), s.getDivisionNo(), s.getAvaliability()));
				}
				return lists;
			}
		}
		if (listofBuildings.isEmpty()) {
			throw new ResourceNotFoundException("No buildings associated with user");
		}
		throw new ResourceNotFoundException("Invalid Building number");
	}

	public String removeSlots(String buildNumber, String slotNumber) {
		User us = authenticationRepository.findById(idheader()).orElse(null);
		List<Building> li = us.getBuildings();
		Iterator<Building> itr = li.iterator();
		while (itr.hasNext()) {
			Building bld = itr.next();
			if (bld.getBuildingNumber().equals(buildNumber)) {
				List<Slot> ls = bld.getSlots();
				Iterator<Slot> its = ls.iterator();
				Slot sl = null;
				while (its.hasNext()) {
					sl = its.next();
					if (sl.getSlotNumber().equals(slotNumber)) {
						ls.remove(sl);
						authenticationRepository.save(us);
						slotRepository.delete(sl);
						return "slot deleted with buildingnumber " + buildNumber;
					}
				}
			}
		}
		throw new ResourceNotFoundException("Building not found with building Number " + buildNumber + " and Slot Number " + slotNumber);
	}

	public String updateSlot(String buildNumber, String slotNumber, SlotDTO slot) {

		User us = authenticationRepository.findById(idheader()).orElse(null);
		List<Building> li = us.getBuildings();
		Iterator<Building> itr = li.iterator();
		Building bld = null;
		while (itr.hasNext()) {
			bld = itr.next();
			if (bld.getBuildingNumber().equals(buildNumber)) {
				List<Slot> ls = bld.getSlots();
				Iterator<Slot> its = ls.iterator();
				Slot sl = null;
				while (its.hasNext()) {
					sl = its.next();
					if (sl.getSlotNumber().equals(slotNumber)) {

						if (sl.getSlotNumber() != null) {
							sl.setSlotNumber(slot.getSlotNumber());
						}
						if (sl.getDivisionNo() != null) {
							sl.setDivisionNo(slot.getDivisionNo());
						}
						if (sl.getFloornumber() != null) {
							sl.setFloornumber(slot.getFloornumber());
						}

						slotRepository.save(sl);
						buildingRepository.save(bld);
						return "slot updated with slotNumber" + slotNumber;
					}
				}

			}
		}
		throw new ResourceNotFoundException("Invalid buildingNumber or slot Id");
	}

}
