
package com.example.demo.major.project.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.major.project.service.BookingService;

@RestController
public class BookingController {
	@Autowired
	private BookingService bookingService;

	@PreAuthorize("hasAuthority('user')")
	@PostMapping(path = RestApiConfig.BOOKING)
	public ResponseEntity<String> bookslot(@PathVariable String buildNumber, @PathVariable String slotNo,
			@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date bookingdate) {
		return ResponseEntity.status(HttpStatus.OK).body(bookingService.bookSlot(buildNumber, slotNo, bookingdate));
	}

	@DeleteMapping(path = RestApiConfig.CANCEL_BOOKING)
	public ResponseEntity<String> cancelslotbooking(@PathVariable Integer slot_bookingId) {
		bookingService.cancelBook(slot_bookingId);
		return ResponseEntity.status(HttpStatus.OK).body("Booking Cancelled with slot_bookingId " + slot_bookingId);
	}
}
