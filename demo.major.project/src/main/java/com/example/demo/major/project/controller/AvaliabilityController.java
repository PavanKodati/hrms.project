package com.example.demo.major.project.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.major.project.DTO.AvaliabilityDTO;
import com.example.demo.major.project.service.AvaliabilityService;

@RestController
public class AvaliabilityController {
	@Autowired
	private AvaliabilityService avaliabilityService;

	@PreAuthorize("hasAuthority('admin')")
	@PostMapping(path = RestApiConfig.AVALIABILITY_CHANGES)
	public ResponseEntity<String> addavailiabilty(@PathVariable String buildNumber, @PathVariable String slotNo,
			@RequestBody AvaliabilityDTO avaliability) {
		avaliabilityService.addAvailabilaty(buildNumber, slotNo, avaliability);
		return ResponseEntity.status(HttpStatus.OK).body("added availiability ");
	}

	@PreAuthorize("hasAuthority('admin')")
	@DeleteMapping(path = RestApiConfig.AVALIABILITY_CHANGES)
	public ResponseEntity<String> removeavAvaliab(@PathVariable String buildNumber, @PathVariable String slotNo) {
		avaliabilityService.removeAvailability(buildNumber, slotNo);
		return ResponseEntity.status(HttpStatus.OK).body("Removed avaliability for slot");
	}

	@GetMapping(path = RestApiConfig.AVALIABILITY)
	public ResponseEntity<List<String>> getavailability(@PathVariable String buildNumber,
			@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
		return ResponseEntity.status(HttpStatus.OK).body(avaliabilityService.getAvailability(buildNumber, date));
	}
}
