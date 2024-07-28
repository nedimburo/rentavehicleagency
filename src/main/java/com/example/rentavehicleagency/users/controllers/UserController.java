package com.example.rentavehicleagency.users.controllers;

import com.example.rentavehicleagency.users.entities.UserEntity;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.rentavehicleagency.businesses.services.BusinessService;
import com.example.rentavehicleagency.clients.services.ClientService;
import com.example.rentavehicleagency.directors.services.DirectorService;
import com.example.rentavehicleagency.employees.services.EmployeeService;
import com.example.rentavehicleagency.vehicleImages.services.VehicleImageService;
import com.example.rentavehicleagency.reports.services.ReportService;
import com.example.rentavehicleagency.requests.services.RequestService;
import com.example.rentavehicleagency.users.services.UserService;
import com.example.rentavehicleagency.vehicles.services.VehicleService;

@Slf4j
@Getter
@RestController
@RequiredArgsConstructor
@RequestMapping("public/user")
@Tags(value = @Tag(name = "Public | User"))
public class UserController {

	private final UserService userService;

	private final VehicleService vehicleService;

	private final VehicleImageService vehicleImageService;

	private final RequestService requestService;
	
	private final ClientService clientService;

	private final EmployeeService employeeService;
	
	private final BusinessService businessService;

	private final DirectorService directorService;

	private final ReportService reportService;
	
	@PostMapping("/register")
	public String submitRegisterForm(@ModelAttribute("user") UserEntity userEntity){
		userService.registerUser(userEntity);
		return "redirect:/login";
	}
	
	@PostMapping("/profile/{id}/submit-image")
	public String submitProfileImage(@PathVariable Long id, @RequestParam("image") MultipartFile image) {
		UserEntity userEntity =userService.getUserById(id);
		userService.setProfileImage(userEntity, image);
		return "redirect:/profile/{id}";
	}

}
