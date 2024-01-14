package com.example.rentavehicleagency.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.rentavehicleagency.models.Business;
import com.example.rentavehicleagency.models.Client;
import com.example.rentavehicleagency.models.Employee;
import com.example.rentavehicleagency.models.ImageVehicle;
import com.example.rentavehicleagency.models.Request;
import com.example.rentavehicleagency.models.User;
import com.example.rentavehicleagency.models.Vehicle;
import com.example.rentavehicleagency.services.BusinessService;
import com.example.rentavehicleagency.services.ClientService;
import com.example.rentavehicleagency.services.EmployeeService;
import com.example.rentavehicleagency.services.ImageVehicleService;
import com.example.rentavehicleagency.services.RequestService;
import com.example.rentavehicleagency.services.UserService;
import com.example.rentavehicleagency.services.VehicleService;

@Controller
public class RequestController {

	@Autowired
	private RequestService requestService;
	
	@Autowired
	private VehicleService vehicleService;
	
	@Autowired
	private ImageVehicleService imageVehicleService;
	
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
		User user=userService.getUserByEmail(principal.getName());
		Client client=clientService.findClientByUserId(user.getId());
		Vehicle vehicle=vehicleService.getVehicleById(vehicleId);
		List<ImageVehicle> vehicleImages=imageVehicleService.getVehicleImages(vehicleId);
		model.addAttribute("vehicle", vehicle);
		model.addAttribute("vehicleImages", vehicleImages);
		model.addAttribute("request", new Request());
		model.addAttribute("client", client);
		return "selectedVehicle";
	}
	
	@GetMapping("/selected-vehicle-guest/{vehicleId}")
	public String requestSelectedVehicleGuestPage(@PathVariable Long vehicleId, Model model) {
		Vehicle vehicle=vehicleService.getVehicleById(vehicleId);
		List<ImageVehicle> vehicleImages=imageVehicleService.getVehicleImages(vehicleId);
		model.addAttribute("vehicle", vehicle);
		model.addAttribute("vehicleImages", vehicleImages);
		return "selectedVehicleGuest";
	}
	
	@PostMapping("/selected-vehicle/{vehicleId}/create-request")
	public String submitRequest(@PathVariable Long vehicleId, @ModelAttribute("request") Request request, Principal principal) {
		User user=userService.getUserByEmail(principal.getName());
		Client client=clientService.findClientByUserId(user.getId());
		Vehicle vehicle=vehicleService.getVehicleById(vehicleId);
		request.setVehicle(vehicle);
		request.setClient(client);
		requestService.saveRequest(request);
		return "redirect:/selected-vehicle/{vehicleId}";
	}
	
	@GetMapping("/handle-request/{id}")
	public String handleRequestPage(@PathVariable Long id, Model model) {
		Request request=requestService.getRequestById(id);
		ImageVehicle vehicleImage=imageVehicleService.getVehicleImages(request.getVehicle().getId()).get(0);
		model.addAttribute("vehicle", request.getVehicle());
		model.addAttribute("client", request.getClient());
		model.addAttribute("clientUser", request.getClient().getUser());
		model.addAttribute("request", request);
		model.addAttribute("vehicleImage", vehicleImage);
		return "handleRequest";
	}
	
	@GetMapping("/handle-request/{id}/approve")
	public String approveRequest(@PathVariable Long id, Principal principal) {
		Request request=requestService.getRequestById(id);
		User user=userService.getUserByEmail(principal.getName());
		Employee employee=employeeService.findEmployeeByUserId(user.getId());
		requestService.setRequestStatus(request, "ONGOING", employee, "None");
		vehicleService.setVehicleStatus(request.getVehicle(), "RENTED");
		return "redirect:/rent-dashboard-page";
	}
	
	@GetMapping("/return-vehicle/{id}")
	public String returnVehicleFromRequest(@PathVariable Long id, Principal principal) {
		Request request=requestService.getRequestById(id);
		User user=userService.getUserByEmail(principal.getName());
		Employee employee=employeeService.findEmployeeByUserId(user.getId());
		Business business=businessService.getBusinessById(employee.getBusiness().getId());
		requestService.setRequestStatus(request, "FINISHED", employee, "None");
		vehicleService.setVehicleStatus(request.getVehicle(), "AVAILABLE");
		businessService.registerProfit(business, request);
		return "redirect:/rent-dashboard-page";
	}
	
	@PostMapping("/deny-request/{id}")
	public String denyRequest(@PathVariable Long id, Principal principal, @RequestParam("note") String note) {
		Request request=requestService.getRequestById(id);
		User user=userService.getUserByEmail(principal.getName());
		Employee employee=employeeService.findEmployeeByUserId(user.getId());
		requestService.setRequestStatus(request, "DENIED", employee, note);
		return "redirect:/rent-dashboard-page";
	}
	
	@GetMapping("/user-requests")
	public String userRequestPage(Principal principal, Model model) {
		User user=userService.getUserByEmail(principal.getName());
		Client client=clientService.findClientByUserId(user.getId());
		List<Request> clientsRequests=requestService.getAllClientsRequestsById(client.getId());
		model.addAttribute("clientsRequests", clientsRequests);
		return "userRequests";
	}
}
