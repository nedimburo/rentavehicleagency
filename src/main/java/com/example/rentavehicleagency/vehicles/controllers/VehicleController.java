package com.example.rentavehicleagency.vehicles.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.rentavehicleagency.vehicles.payloads.VehicleRequestDto;
import com.example.rentavehicleagency.vehicles.services.VehicleService;

@Slf4j
@Getter
@RestController
@RequiredArgsConstructor
@RequestMapping("owner/vehicle")
@Tags(value = @Tag(name = "Owner | Vehicle"))
public class VehicleController {

	private final VehicleService service;
	
	@PostMapping("/add-vehicle")
	public ResponseEntity<?> addVehicle(@RequestBody VehicleRequestDto vehicleRequestDto) {
		return service.addVehicle(vehicleRequestDto);
	}
	
	@GetMapping("/remove-vehicle/{id}")
	public ResponseEntity<?> removeVehicle(@PathVariable Long id) {
		return service.removeVehicle(id);
	}
}
