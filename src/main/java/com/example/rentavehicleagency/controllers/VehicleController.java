package com.example.rentavehicleagency.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.rentavehicleagency.dto.VehicleDto;
import com.example.rentavehicleagency.models.Business;
import com.example.rentavehicleagency.models.Vehicle;
import com.example.rentavehicleagency.services.BusinessService;
import com.example.rentavehicleagency.services.ImageVehicleService;
import com.example.rentavehicleagency.services.VehicleService;

@Controller
public class VehicleController {

	@Autowired
	private VehicleService vehicleService;
	
	@Autowired
	private BusinessService businessService;
	
	@Autowired
	private ImageVehicleService imageVehicleService;
	
	@GetMapping("/add-vehicle")
	public String addVehiclePage(Model model) {
		List<Business> allBusinesses=businessService.getAllBusinesses();
		model.addAttribute("vehicleDto", new VehicleDto());
		model.addAttribute("allBusinesses", allBusinesses);
		return "addVehicle";
	}
	
	@PostMapping("/add-vehicle-submit")
	public String submitVehicle(@ModelAttribute("vehicleDto") VehicleDto vehicleDto) {
		Business business=businessService.findBusinessByName(vehicleDto.getBusinessName());
		Vehicle vehicle=new Vehicle(vehicleDto.getBrand(), vehicleDto.getModelName(), vehicleDto.getModelYear(),
				vehicleDto.getRegistrationPlate(), vehicleDto.getEngineDisplacement(), vehicleDto.getHorsepower(),
				vehicleDto.getFuel(), vehicleDto.getTransmission(), vehicleDto.getColor(), vehicleDto.getType(),
				vehicleDto.getBodyShape(), vehicleDto.getStatus(), vehicleDto.getPrice(), vehicleDto.getAddedDate(), business);
		vehicleService.saveVehicle(vehicle);
		Vehicle newVehicle=vehicleService.findVehicleByRegistrationPlate(vehicleDto.getRegistrationPlate());
		imageVehicleService.saveListImageVehicle(vehicleDto.getImages(), newVehicle);
		return "redirect:/owner-page";
	}
	
	@GetMapping("/remove-vehicle/{id}")
	public String removeVehicle(@PathVariable Long id) {
		vehicleService.removeVehicle(id);
		return "redirect:/owner-page";
	}
}
