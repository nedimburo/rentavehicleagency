package com.example.rentavehicleagency.vehicles.controllers;

import com.example.rentavehicleagency.vehicles.entities.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import com.example.rentavehicleagency.vehicles.payloads.VehicleDto;
import com.example.rentavehicleagency.businesses.entities.BusinessEntity;
import com.example.rentavehicleagency.businesses.services.BusinessService;
import com.example.rentavehicleagency.vehicleImages.services.VehicleImageService;
import com.example.rentavehicleagency.vehicles.services.VehicleService;

@Slf4j
@Getter
@RestController
@RequiredArgsConstructor
@RequestMapping("public/vehicle")
@Tags(value = @Tag(name = "Public | Vehicle"))
public class VehicleController {

	private final VehicleService vehicleService;

	private final BusinessService businessService;

	private final VehicleImageService vehicleImageService;
	
	@PostMapping("/add-vehicle-submit")
	public String submitVehicle(@ModelAttribute("vehicleDto") VehicleDto vehicleDto) {
		BusinessEntity businessEntity =businessService.findBusinessByName(vehicleDto.getBusinessName());
		VehicleEntity vehicleEntity =new VehicleEntity();
		vehicleEntity.setBrand(vehicleDto.getBrand());
		vehicleEntity.setModelName(vehicleDto.getModelName());
		vehicleEntity.setModelYear(vehicleDto.getModelYear());
		vehicleEntity.setRegistrationPlate(vehicleDto.getRegistrationPlate());
		vehicleEntity.setEngineDisplacement(vehicleDto.getEngineDisplacement());
		vehicleEntity.setHorsepower(vehicleDto.getHorsepower());
		vehicleEntity.setFuel(FuelType.valueOf(vehicleDto.getFuel()));
		vehicleEntity.setTransmission(TransmissionType.valueOf(vehicleDto.getTransmission()));
		vehicleEntity.setColor(vehicleDto.getColor());
		vehicleEntity.setType(VehicleType.valueOf(vehicleDto.getType()));
		vehicleEntity.setBodyShape(BodyShape.valueOf(vehicleDto.getBodyShape()));
		vehicleEntity.setStatus(vehicleDto.getStatus());
		vehicleEntity.setPrice(vehicleDto.getPrice());
		vehicleEntity.setAddedDate(vehicleDto.getAddedDate());
		vehicleEntity.setBusinessEntity(businessEntity);
		vehicleService.saveVehicle(vehicleEntity);
		VehicleEntity newVehicleEntity =vehicleService.findVehicleByRegistrationPlate(vehicleDto.getRegistrationPlate());
		vehicleImageService.saveListImageVehicle(vehicleDto.getImages(), newVehicleEntity);
		return "redirect:/owner-page";
	}
	
	@GetMapping("/remove-vehicle/{id}")
	public String removeVehicle(@PathVariable Long id) {
		vehicleService.removeVehicle(id);
		return "redirect:/owner-page";
	}
}
