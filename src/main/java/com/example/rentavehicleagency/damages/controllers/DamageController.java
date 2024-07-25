package com.example.rentavehicleagency.damages.controllers;

import java.security.Principal;
import java.time.LocalDate;

import com.example.rentavehicleagency.damages.entities.DamageEntity;
import com.example.rentavehicleagency.users.entities.UserEntity;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.rentavehicleagency.employees.entities.EmployeeEntity;
import com.example.rentavehicleagency.vehicleImages.entities.VehicleImageEntity;
import com.example.rentavehicleagency.vehicles.entities.VehicleEntity;
import com.example.rentavehicleagency.damages.services.DamageService;
import com.example.rentavehicleagency.employees.services.EmployeeService;
import com.example.rentavehicleagency.vehicleImages.services.VehicleImageService;
import com.example.rentavehicleagency.users.services.UserService;
import com.example.rentavehicleagency.vehicles.services.VehicleService;

@Slf4j
@Getter
@RestController
@RequiredArgsConstructor
@RequestMapping("public/damage")
@Tags(value = {@Tag(name = "Public | Damage"), @Tag(name = "operationIdNamePublicDamage")})
public class DamageController {

	private final DamageService damageService;

	private final VehicleService vehicleService;

	private final VehicleImageService vehicleImageService;
	
	private final UserService userService;
	
	private final EmployeeService employeeService;
	
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
