package com.example.rentavehicleagency.requests.controllers;

import java.security.Principal;

import com.example.rentavehicleagency.clients.entities.ClientEntity;
import com.example.rentavehicleagency.requests.entities.RequestEntity;
import com.example.rentavehicleagency.users.entities.UserEntity;
import com.example.rentavehicleagency.vehicles.entities.VehicleEntity;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import com.example.rentavehicleagency.businesses.entities.BusinessEntity;
import com.example.rentavehicleagency.employees.entities.EmployeeEntity;
import com.example.rentavehicleagency.businesses.services.BusinessService;
import com.example.rentavehicleagency.clients.services.ClientService;
import com.example.rentavehicleagency.employees.services.EmployeeService;
import com.example.rentavehicleagency.vehicleImages.services.VehicleImageService;
import com.example.rentavehicleagency.requests.services.RequestService;
import com.example.rentavehicleagency.users.services.UserService;
import com.example.rentavehicleagency.vehicles.services.VehicleService;

@Slf4j
@Getter
@RestController
@RequiredArgsConstructor
@RequestMapping("public/request")
@Tags(value = {@Tag(name = "Public | Request"), @Tag(name = "operationIdNamePublicRequest")})
public class RequestController {

	private final RequestService requestService;

	private final VehicleService vehicleService;
	
	private final VehicleImageService vehicleImageService;
	
	private final UserService userService;
	
	private final ClientService clientService;

	private final EmployeeService employeeService;

	private final BusinessService businessService;
	
	@PostMapping("/selected-vehicle/{vehicleId}/create-request")
	public String submitRequest(@PathVariable Long vehicleId, @ModelAttribute("request") RequestEntity requestEntity, Principal principal) {
		UserEntity userEntity =userService.getUserByEmail(principal.getName());
		ClientEntity clientEntity =clientService.findClientByUserId(userEntity.getId());
		VehicleEntity vehicleEntity =vehicleService.getVehicleById(vehicleId);
		requestEntity.setVehicleEntity(vehicleEntity);
		requestEntity.setClientEntity(clientEntity);
		requestService.saveRequest(requestEntity);
		return "redirect:/selected-vehicle/{vehicleId}";
	}
	
	@GetMapping("/handle-request/{id}/approve")
	public String approveRequest(@PathVariable Long id, Principal principal) {
		RequestEntity requestEntity =requestService.getRequestById(id);
		UserEntity userEntity =userService.getUserByEmail(principal.getName());
		EmployeeEntity employeeEntity =employeeService.findEmployeeByUserId(userEntity.getId());
		requestService.setRequestStatus(requestEntity, "ONGOING", employeeEntity, "None");
		vehicleService.setVehicleStatus(requestEntity.getVehicleEntity(), "RENTED");
		return "redirect:/rent-dashboard-page";
	}
	
	@GetMapping("/return-vehicle/{id}")
	public String returnVehicleFromRequest(@PathVariable Long id, Principal principal) {
		RequestEntity requestEntity =requestService.getRequestById(id);
		UserEntity userEntity =userService.getUserByEmail(principal.getName());
		EmployeeEntity employeeEntity =employeeService.findEmployeeByUserId(userEntity.getId());
		BusinessEntity businessEntity =businessService.getBusinessById(employeeEntity.getBusinessEntity().getId());
		requestService.setRequestStatus(requestEntity, "FINISHED", employeeEntity, "None");
		vehicleService.setVehicleStatus(requestEntity.getVehicleEntity(), "AVAILABLE");
		businessService.registerProfit(businessEntity, requestEntity);
		return "redirect:/rent-dashboard-page";
	}
	
	@PostMapping("/deny-request/{id}")
	public String denyRequest(@PathVariable Long id, Principal principal, @RequestParam("note") String note) {
		RequestEntity requestEntity =requestService.getRequestById(id);
		UserEntity userEntity =userService.getUserByEmail(principal.getName());
		EmployeeEntity employeeEntity =employeeService.findEmployeeByUserId(userEntity.getId());
		requestService.setRequestStatus(requestEntity, "DENIED", employeeEntity, note);
		return "redirect:/rent-dashboard-page";
	}
}
