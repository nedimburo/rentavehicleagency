package com.example.rentavehicleagency.vehicles.controllers;

import java.util.List;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.rentavehicleagency.vehicles.payloads.VehicleDto;
import com.example.rentavehicleagency.businesses.entities.BusinessEntity;
import com.example.rentavehicleagency.vehicles.entities.VehicleEntity;
import com.example.rentavehicleagency.businesses.services.BusinessService;
import com.example.rentavehicleagency.vehicleImages.services.VehicleImageService;
import com.example.rentavehicleagency.vehicles.services.VehicleService;

@Slf4j
@Getter
@RestController
@RequiredArgsConstructor
@RequestMapping("public/vehicle")
@Tags(value = {@Tag(name = "Public | Vehicle"), @Tag(name = "operationIdNamePublicVehicle")})
public class VehicleController {

	private final VehicleService vehicleService;

	private final BusinessService businessService;

	private final VehicleImageService vehicleImageService;
	
	@GetMapping("/add-vehicle")
	public String addVehiclePage(Model model) {
		List<BusinessEntity> allBusinessEntities =businessService.getAllBusinesses();
		model.addAttribute("vehicleDto", new VehicleDto());
		model.addAttribute("allBusinesses", allBusinessEntities);
		return "addVehicle";
	}
	
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
		vehicleEntity.setFuel(vehicleDto.getFuel());
		vehicleEntity.setTransmission(vehicleDto.getTransmission());
		vehicleEntity.setColor(vehicleDto.getColor());
		vehicleEntity.setType(vehicleDto.getType());
		vehicleEntity.setBodyShape(vehicleDto.getBodyShape());
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
