package com.example.demo.major.project.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.demo.major.project.DTO.AvaliabilityDTO;
import com.example.demo.major.project.domain.Avaliability;
import com.example.demo.major.project.domain.Booking;
import com.example.demo.major.project.domain.Building;
import com.example.demo.major.project.domain.Slot;

import com.example.demo.major.project.domain.User;
import com.example.demo.major.project.exception.ResourceNotFoundException;
import com.example.demo.major.project.repository.AuthenticationRepository;
import com.example.demo.major.project.repository.AvaliabilityRepository;

import com.example.demo.major.project.repository.BuildingRepository;
import com.example.demo.major.project.repository.SlotRepository;

@Service
public class AvaliabilityService {
	@Autowired
	private AuthenticationRepository authenticationRepository;
	@Autowired
	private BuildingRepository buildingRepository;
	@Autowired
	private SlotRepository slotRepository;
	@Autowired
	private AvaliabilityRepository avaliabilityRepository;

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

	public String addAvailabilaty(String buildNumber, String slotNo, AvaliabilityDTO avaliability) {
		Avaliability ava=new Avaliability();
		
		User user = authenticationRepository.findById(idheader()).orElse(null);

		List<Building> buildingslist = user.getBuildings();

		Iterator<Building> itr = buildingslist.iterator();

		Building buil = null;
		while (itr.hasNext()) {
			buil = itr.next();
			if (buil.getBuildingNumber().equals(buildNumber)) {
				Slot sl = null;
				List<Slot> slots = buil.getSlots();
				Iterator<Slot> itrs = slots.iterator();

				while (itrs.hasNext()) {
					sl = itrs.next();
					if (sl.getSlotNumber().equals(slotNo)) {
						ava.setFromDate(avaliability.getFromDate());
						ava.setToDate(avaliability.getToDate());
						ava.setBookings(avaliability.getBookings());
						
						sl.setAvaliability(ava);

						slotRepository.save(sl);

						return "avaliability added to Slotnumber " + slotNo + " of buildingNumber " + buildNumber;
					}
				}
			}
		}
		throw new ResourceNotFoundException("Invalid SlotNumber " + slotNo + " or Building Number " + buildNumber);
	}

	public List<String> getAvailability(String buildingNumber, Date date) {
		String role = getRoleHeader();
		Integer userId = idheader();
		ArrayList<String> slots = new ArrayList<String>();
     		if (role.equals("admin")) {
			User user = authenticationRepository.findById(userId).orElse(null);
			if (user.getBuildings().isEmpty())
				throw new ResourceNotFoundException("Building are not present for this Admin");
			List<Building> listB = user.getBuildings();
			Building building = null;
			Iterator<Building> itb = listB.iterator();
			while (itb.hasNext()) {
				building = itb.next();
				if (building.getBuildingNumber().equals(buildingNumber)) {
					if (building.getSlots().isEmpty()) {
						throw new ResourceNotFoundException("No Slots Available for This Building");
					}
					List<Slot> listS = building.getSlots();
					Iterator<Slot> its = listS.iterator();
					Slot slot = null;
					while (its.hasNext()) {
						slot = its.next();
						if (slot.getAvaliability() != null) {
							Avaliability ava = slot.getAvaliability();
							if (date.after(ava.getFromDate()) && date.before(ava.getToDate())) {
								if (ava.getBookings().isEmpty()) {
									slots.add(slot.getSlotNumber());
								} else {
									List<Booking> listBook = ava.getBookings();
									Iterator<Booking> it = listBook.iterator();
									Booking bk;
									boolean booking = true;
									while (it.hasNext()) {
										bk = it.next();
										if (bk.getBookingDate().equals(date)) {
											booking = false;
											break;
										}
									}
									if (booking) {
										slots.add(slot.getSlotNumber());
									}
								}

							}
						}

					}
					if (slots.isEmpty()) {
						throw new ResourceNotFoundException("No Slots are present in that Building");
					} else
						return slots;
				}
				throw new ResourceNotFoundException("Invalid Building Id");
			}
		} else {

			List<Building> list = buildingRepository.findAll();
			Iterator<Building> itB = list.iterator();
			Building building = null;
			while (itB.hasNext()) {
				building = itB.next();
				if (building.getBuildingNumber().equals(buildingNumber)) {

					List<Slot> listS = building.getSlots();
					Iterator<Slot> itS = listS.iterator();
					Slot s = null;
					while (itS.hasNext()) {
						s = itS.next();
						if (s.getAvaliability() != null) {
							Avaliability availability = s.getAvaliability();

							if (date.after(availability.getFromDate()) && date.before(availability.getToDate())) {
								if (availability.getBookings().isEmpty()) {
									slots.add(s.getSlotNumber());
								} else {
									List<Booking> listBook = availability.getBookings();
									Iterator<Booking> it = listBook.iterator();
									Booking bk;
									boolean booking = true;
									while (it.hasNext()) {
										bk = it.next();
										if (bk.getBookingDate().equals(date)) {
											booking = false;
											break;
										}
									}
									if (booking) {
										System.out.println("Inside bookings");
										slots.add(s.getSlotNumber());
									}
								}

							}

						}
					}
					if (slots.isEmpty())
						throw new ResourceNotFoundException("No Slots are present in that Building for booking");
					else
						return slots;
				}
			}
			throw new ResourceNotFoundException("No Buildings Available for this user");
		}

		throw new ResourceNotFoundException("Invalid User");
	}

	public String removeAvailability(String buildNumber, String slotNo) {

		Integer userId = idheader();
		User us = authenticationRepository.findById(userId).orElse(null);

		if (us.getBuildings().isEmpty()) {
			throw new ResourceNotFoundException("No buildings available");
		}
		List<Building> listB = us.getBuildings();
		Iterator<Building> itB = listB.iterator();
		Building bld = null;
		while (itB.hasNext()) {
			bld = itB.next();
			if (bld.getBuildingNumber().equals(buildNumber)) {
				if (bld.getSlots().isEmpty())
					throw new ResourceNotFoundException("No slots available");
				List<Slot> listS = bld.getSlots();
				Iterator<Slot> itS = listS.iterator();
				Slot slo = null;
				while (itS.hasNext()) {
					slo = itS.next();
					if (slo.getSlotNumber().equals(slotNo)) {

						if (slo.getAvaliability().getBookings().isEmpty()) {

							Avaliability avail = slo.getAvaliability();
							slo.setAvaliability(null);
							slotRepository.save(slo);
							avaliabilityRepository.delete(avail);
							return "Deleted Availabilty";
						}
					}
				}
				throw new ResourceNotFoundException("Invalid slot number");

			}
		}
		throw new ResourceNotFoundException("Invalid building number");
	}

}
