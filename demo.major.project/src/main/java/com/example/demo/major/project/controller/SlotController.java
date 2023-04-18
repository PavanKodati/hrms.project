package com.example.demo.major.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.major.project.DTO.SlotDTO;
import com.example.demo.major.project.service.SlotService;

@RestController
public class SlotController {
	@Autowired
	private SlotService slotService;
	
	@PreAuthorize("hasAuthority('admin')")
	@PostMapping(path = RestApiConfig.SLOTS)
	public ResponseEntity<String> slotadd(@PathVariable String buildingNumber, @RequestBody SlotDTO slots) {
		slotService.addSlot(buildingNumber, slots);
          return ResponseEntity.status(HttpStatus.CREATED).body("Slots added to the Building number "+buildingNumber);	
	}

	@PreAuthorize("hasAuthority('admin')")
	@GetMapping(path = RestApiConfig.SLOTS)
	public ResponseEntity<List<SlotDTO>> findAllSlots(@PathVariable String buildingNumber) {
		List<SlotDTO> slots= slotService.getAllSlots(buildingNumber);		
		 return ResponseEntity.status(HttpStatus.OK).body(slots);
	}

	@PreAuthorize("hasAuthority('admin')")
	@DeleteMapping(path = RestApiConfig.SLOT_CHANGES)
	public ResponseEntity<String> deleteSlot(@PathVariable String buildingNumber, @PathVariable String slotNumber) {
		 slotService.removeSlots(buildingNumber, slotNumber);
		 return ResponseEntity.status(HttpStatus.OK).body("Slot Removed with SlotNumber "+slotNumber);
	}
	

	@PreAuthorize("hasAuthority('admin')")
	@PutMapping(path = RestApiConfig.SLOT_CHANGES)
	public ResponseEntity<String> updateSlot(@PathVariable String buildingNumber, @PathVariable String slotNumber,
			@RequestBody SlotDTO slot) {
		 slotService.updateSlot(buildingNumber, slotNumber, slot);
		 return ResponseEntity.status(HttpStatus.OK).body("Slot updated");
	}
}
