package com.example.rentavehicleagency.requests.controllers;

import java.security.Principal;
import java.util.List;

import com.example.rentavehicleagency.clients.entities.ClientEntity;
import com.example.rentavehicleagency.requests.entities.RequestEntity;
import com.example.rentavehicleagency.users.entities.UserEntity;
import com.example.rentavehicleagency.vehicles.entities.VehicleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.rentavehicleagency.businesses.entities.BusinessEntity;
import com.example.rentavehicleagency.employees.entities.EmployeeEntity;
import com.example.rentavehicleagency.vehicleImages.entities.VehicleImageEntity;
import com.example.rentavehicleagency.businesses.services.BusinessService;
import com.example.rentavehicleagency.clients.services.ClientService;
import com.example.rentavehicleagency.employees.services.EmployeeService;
import com.example.rentavehicleagency.vehicleImages.services.VehicleImageService;
import com.example.rentavehicleagency.requests.services.RequestService;
import com.example.rentavehicleagency.users.services.UserService;
import com.example.rentavehicleagency.vehicles.services.VehicleService;

@Controller
public class RequestController {

	@Autowired
	private RequestService requestService;
	
	@Autowired
	private VehicleService vehicleService;
	
	@Autowired
	private VehicleImageService vehicleImageService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private BusinessService businessService;
	
	@GetMapping("/selected-vehicle/{vehicleId}")
	public String requestSelectedVehiclePage(@PathVariable Long vehicleId, Model model, Principal principal) {
		UserEntity userEntity =userService.getUserByEmail(principal.getName());
		ClientEntity clientEntity =clientService.findClientByUserId(userEntity.getId());
		VehicleEntity vehicleEntity =vehicleService.getVehicleById(vehicleId);
		List<VehicleImageEntity> vehicleImages= vehicleImageService.getVehicleImages(vehicleId);
		model.addAttribute("vehicle", vehicleEntity);
		model.addAttribute("vehicleImages", vehicleImages);
		model.addAttribute("request", new RequestEntity());
		model.addAttribute("client", clientEntity);
		return "selectedVehicle";
	}
	
	@GetMapping("/selected-vehicle-guest/{vehicleId}")
	public String requestSelectedVehicleGuestPage(@PathVariable Long vehicleId, Model model) {
		VehicleEntity vehicleEntity =vehicleService.getVehicleById(vehicleId);
		List<VehicleImageEntity> vehicleImages= vehicleImageService.getVehicleImages(vehicleId);
		model.addAttribute("vehicle", vehicleEntity);
		model.addAttribute("vehicleImages", vehicleImages);
		return "selectedVehicleGuest";
	}
	
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
	
	@GetMapping("/handle-request/{id}")
	public String handleRequestPage(@PathVariable Long id, Model model) {
		RequestEntity requestEntity =requestService.getRequestById(id);
		VehicleImageEntity vehicleImage= vehicleImageService.getVehicleImages(requestEntity.getVehicleEntity().getId()).get(0);
		model.addAttribute("vehicle", requestEntity.getVehicleEntity());
		model.addAttribute("client", requestEntity.getClientEntity());
		model.addAttribute("clientUser", requestEntity.getClientEntity().getUserEntity());
		model.addAttribute("request", requestEntity);
		model.addAttribute("vehicleImage", vehicleImage);
		return "handleRequest";
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
	
	@GetMapping("/user-requests")
	public String userRequestPage(Principal principal, Model model) {
		UserEntity userEntity =userService.getUserByEmail(principal.getName());
		ClientEntity clientEntity =clientService.findClientByUserId(userEntity.getId());
		List<RequestEntity> clientsRequestEntities =requestService.getAllClientsRequestsById(clientEntity.getId());
		model.addAttribute("clientsRequests", clientsRequestEntities);
		return "userRequests";
	}
}
