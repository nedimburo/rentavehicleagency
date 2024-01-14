package com.example.rentavehicleagency.controllers;

import java.security.Principal;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.rentavehicleagency.models.Damage;
import com.example.rentavehicleagency.models.Employee;
import com.example.rentavehicleagency.models.ImageVehicle;
import com.example.rentavehicleagency.models.User;
import com.example.rentavehicleagency.models.Vehicle;
import com.example.rentavehicleagency.services.DamageService;
import com.example.rentavehicleagency.services.EmployeeService;
import com.example.rentavehicleagency.services.ImageVehicleService;
import com.example.rentavehicleagency.services.UserService;
import com.example.rentavehicleagency.services.VehicleService;

@Controller
public class DamageController {

	@Autowired
	private DamageService damageService;
	
	@Autowired
	private VehicleService vehicleService;
	
	@Autowired
	private ImageVehicleService imageVehicleService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/report-damage/{vehicleId}")
	public String reportDamagePage(@PathVariable Long vehicleId, Model model) {
		Vehicle vehicle=vehicleService.getVehicleById(vehicleId);
		ImageVehicle vehicleImage=imageVehicleService.getVehicleImages(vehicleId).get(0);
		model.addAttribute("vehicle", vehicle);
		model.addAttribute("vehicleImage", vehicleImage);
		model.addAttribute("damage", new Damage());
		return "addDamage";
	}
	
	@PostMapping("/report-damage/{vehicleId}/submit")
	public String submitDamage(@PathVariable Long vehicleId, Principal principal, @ModelAttribute("damage") Damage damage) {
		User user=userService.getUserByEmail(principal.getName());
		Employee employee=employeeService.findEmployeeByUserId(user.getId());
		Vehicle vehicle=vehicleService.getVehicleById(vehicleId);
		damage.setEmployee(employee);
		damage.setVehicle(vehicle);
		vehicleService.setVehicleStatus(vehicle, "DAMAGED");
		damageService.saveDamage(damage);
		return "redirect:/maintenance-dashboard-page";
	}
	
	@GetMapping("/record-fix/{vehicleId}")
	public String recordFix(@PathVariable Long vehicleId, Principal principal) {
		User user=userService.getUserByEmail(principal.getName());
		Employee employee=employeeService.findEmployeeByUserId(user.getId());
		Vehicle vehicle=vehicleService.getVehicleById(vehicleId);
		Damage activeDamage=damageService.getActiveDamage(vehicleId);
		vehicleService.setVehicleStatus(vehicle, "AVAILABLE");
		activeDamage.setFixDate(LocalDate.now());
		activeDamage.setEmployee(employee);
		damageService.resolveDamage(activeDamage);
		return "redirect:/maintenance-dashboard-page";
	}
}
