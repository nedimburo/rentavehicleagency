package com.example.rentavehicleagency.damages.controllers;

import java.security.Principal;
import java.time.LocalDate;

import com.example.rentavehicleagency.damages.entities.DamageEntity;
import com.example.rentavehicleagency.users.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.rentavehicleagency.employees.entities.EmployeeEntity;
import com.example.rentavehicleagency.vehicleImages.entities.VehicleImageEntity;
import com.example.rentavehicleagency.vehicles.entities.VehicleEntity;
import com.example.rentavehicleagency.damages.services.DamageService;
import com.example.rentavehicleagency.employees.services.EmployeeService;
import com.example.rentavehicleagency.vehicleImages.services.VehicleImageService;
import com.example.rentavehicleagency.users.services.UserService;
import com.example.rentavehicleagency.vehicles.services.VehicleService;

@Controller
public class DamageController {

	@Autowired
	private DamageService damageService;
	
	@Autowired
	private VehicleService vehicleService;
	
	@Autowired
	private VehicleImageService vehicleImageService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/report-damage/{vehicleId}")
	public String reportDamagePage(@PathVariable Long vehicleId, Model model) {
		VehicleEntity vehicleEntity =vehicleService.getVehicleById(vehicleId);
		VehicleImageEntity vehicleImage= vehicleImageService.getVehicleImages(vehicleId).get(0);
		model.addAttribute("vehicle", vehicleEntity);
		model.addAttribute("vehicleImage", vehicleImage);
		model.addAttribute("damage", new DamageEntity());
		return "addDamage";
	}
	
	@PostMapping("/report-damage/{vehicleId}/submit")
	public String submitDamage(@PathVariable Long vehicleId, Principal principal, @ModelAttribute("damage") DamageEntity damageEntity) {
		UserEntity userEntity =userService.getUserByEmail(principal.getName());
		EmployeeEntity employeeEntity =employeeService.findEmployeeByUserId(userEntity.getId());
		VehicleEntity vehicleEntity =vehicleService.getVehicleById(vehicleId);
		damageEntity.setEmployeeEntity(employeeEntity);
		damageEntity.setVehicleEntity(vehicleEntity);
		vehicleService.setVehicleStatus(vehicleEntity, "DAMAGED");
		damageService.saveDamage(damageEntity);
		return "redirect:/maintenance-dashboard-page";
	}
	
	@GetMapping("/record-fix/{vehicleId}")
	public String recordFix(@PathVariable Long vehicleId, Principal principal) {
		UserEntity userEntity =userService.getUserByEmail(principal.getName());
		EmployeeEntity employeeEntity =employeeService.findEmployeeByUserId(userEntity.getId());
		VehicleEntity vehicleEntity =vehicleService.getVehicleById(vehicleId);
		DamageEntity activeDamageEntity =damageService.getActiveDamage(vehicleId);
		vehicleService.setVehicleStatus(vehicleEntity, "AVAILABLE");
		activeDamageEntity.setFixDate(LocalDate.now());
		activeDamageEntity.setEmployeeEntity(employeeEntity);
		damageService.resolveDamage(activeDamageEntity);
		return "redirect:/maintenance-dashboard-page";
	}
}
