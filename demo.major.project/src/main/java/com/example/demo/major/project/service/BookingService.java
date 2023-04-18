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

import com.example.demo.major.project.domain.Avaliability;
import com.example.demo.major.project.domain.Booking;
import com.example.demo.major.project.domain.Building;
import com.example.demo.major.project.domain.Slot;
import com.example.demo.major.project.domain.User;
import com.example.demo.major.project.exception.ResourceNotFoundException;
import com.example.demo.major.project.repository.AuthenticationRepository;
import com.example.demo.major.project.repository.AvaliabilityRepository;
import com.example.demo.major.project.repository.BookingRepository;
import com.example.demo.major.project.repository.BuildingRepository;

@Service
public class BookingService {

	@Autowired
	private AuthenticationRepository authenticationRepository;
	@Autowired
	private BuildingRepository buildingRepository;
	@Autowired
	private AvaliabilityRepository avaliabilityRepository;
	@Autowired
	private BookingRepository bookingRepository;

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

	public String bookSlot(String buildNumber, String slotNo, Date bookingdate) {
		Integer userId = idheader();
		boolean booking = true;
		List<Booking> abook = new ArrayList<Booking>();
		List<Building> listB = buildingRepository.findAll();

		Iterator<Building> listofBuildings = listB.iterator();
		Building build = null;
		while (listofBuildings.hasNext()) {
			build = listofBuildings.next();

			if (build.getBuildingNumber().equals(buildNumber)) {

				if (build.getSlots().isEmpty()) {
					throw new ResourceNotFoundException("No slots are present in this building");
				}
				List<Slot> slots = build.getSlots();
				Iterator<Slot> lists = slots.iterator();
				Slot slot = null;
				while (lists.hasNext()) {
					slot = lists.next();
					if (slot.getSlotNumber().equals(slotNo)) {
						if (slot.getAvaliability() == null) {
							throw new ResourceNotFoundException("no availability for this slot");
						}
						Avaliability avail = slot.getAvaliability();

						if (bookingdate.after(avail.getFromDate()) && bookingdate.before(avail.getToDate())) {

							if (avail.getBookings().isEmpty()) {
								User user = authenticationRepository.getById(userId);
								Booking slotbook = new Booking();
								slotbook.setBookingDate(bookingdate);
								slotbook.setUser(user);
								slotbook.setSlot(slot);
								System.out.println("empty");

								abook.add(slotbook);
								avail.setBookings(abook);

								avaliabilityRepository.save(avail);

								return "Booking Sucessfull";

							} else {

								Iterator<Booking> itrsb = avail.getBookings().iterator();
								Booking sb = null;
								while (itrsb.hasNext()) {
									sb = itrsb.next();
									if (sb.getBookingDate().equals(bookingdate)) {
										booking = false;
										break;
									}
								}
								if (booking) {
									User user = authenticationRepository.getById(userId);

									Booking slotbook = new Booking();
									slotbook.setBookingDate(bookingdate);
									slotbook.setUser(user);
									slotbook.setSlot(slot);
									abook = avail.getBookings();
									abook.add(slotbook);
									avail.setBookings(abook);
									System.out.println("in");
									avaliabilityRepository.save(avail);
									return "Booking Sucessfull";
								} else {
									throw new ResourceNotFoundException("Already booked");
								}
							}
						} else
							throw new ResourceNotFoundException("not available on the date " + bookingdate);
					}
				}
			}

		}
		throw new ResourceNotFoundException("Building Number not valid =" + buildNumber);
	}

	public String cancelBook(Integer slot_bookingId) {
		Integer userId = idheader();
		Booking booking = bookingRepository.findById(slot_bookingId).orElse(null);
		if (booking != null) {
			Date date = new Date();
			if (booking.getUser().getId().equals(userId)) {
				if (date.compareTo(booking.getBookingDate()) < 0) {
					bookingRepository.delete(booking);
					return "Booking Cancelled";
				}
			}
		}
		throw new ResourceNotFoundException("enter valid slot booking Id " + slot_bookingId + " is not valid");
	}

}
