package com.example.demo.major.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.major.project.DTO.BuildingDTO;
import com.example.demo.major.project.service.BuildingService;

@RestController
public class BuildingController {

	@Autowired
	private BuildingService buildingService;

	@PreAuthorize("hasAuthority('admin')")
	@DeleteMapping(path = RestApiConfig.BUILDING_CHANGES)
	public ResponseEntity<String> deleteBuildingbyno(@PathVariable String buildingNumber) {
		buildingService.deleteBuilding(buildingNumber);
		return ResponseEntity.status(HttpStatus.OK).body("Building Deleted");
	}

	@GetMapping(path = RestApiConfig.BUILDING)
	public ResponseEntity<List<BuildingDTO>> Buildings() {
		return ResponseEntity.status(HttpStatus.FOUND).body(buildingService.getallbuildings());
	}

	@PostMapping(path = RestApiConfig.BUILDING)
	@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<String> addBuilding(@Validated @RequestBody BuildingDTO building) {
		buildingService.addBuilding(building);
		return ResponseEntity.status(HttpStatus.CREATED).body("Building Added");
	}

	@PreAuthorize("hasAuthority('admin')")
	@PutMapping(path = RestApiConfig.BUILDING_CHANGES)
	public ResponseEntity<String> updatebuilding(@PathVariable String buildingNumber,
			@RequestBody BuildingDTO building) {
		buildingService.updateBuildings(buildingNumber, building);
		return ResponseEntity.status(HttpStatus.OK).body("Building updated");
	}

	@GetMapping(path = RestApiConfig.BUILDING_PIN)
	public ResponseEntity<List<BuildingDTO>> Buidingsbypincode(@PathVariable Integer pincode) {
		return ResponseEntity.status(HttpStatus.FOUND).body(buildingService.getByPinCodeBuildings(pincode));
	}

	@GetMapping(path = RestApiConfig.BUILDING_NAME)
	public ResponseEntity<List<BuildingDTO>> Buidingsbyname(@PathVariable String buildingName) {

		return ResponseEntity.status(HttpStatus.FOUND).body(buildingService.getByNameBuildings(buildingName));
	}

}
