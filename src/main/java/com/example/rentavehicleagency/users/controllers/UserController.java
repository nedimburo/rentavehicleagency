package com.example.rentavehicleagency.users.controllers;

import java.security.Principal;
import java.util.List;

import com.example.rentavehicleagency.businesses.entities.BusinessEntity;
import com.example.rentavehicleagency.employees.entities.EmployeeEntity;
import com.example.rentavehicleagency.users.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.rentavehicleagency.requests.payloads.RequestDto;
import com.example.rentavehicleagency.vehicles.payloads.VehicleDisplayDto;
import com.example.rentavehicleagency.clients.entities.ClientEntity;
import com.example.rentavehicleagency.directors.entities.DirectorEntity;
import com.example.rentavehicleagency.vehicleImages.entities.VehicleImageEntity;
import com.example.rentavehicleagency.reports.entities.ReportEntity;
import com.example.rentavehicleagency.requests.entities.RequestEntity;
import com.example.rentavehicleagency.vehicles.entities.VehicleEntity;
import com.example.rentavehicleagency.businesses.services.BusinessService;
import com.example.rentavehicleagency.clients.services.ClientService;
import com.example.rentavehicleagency.directors.services.DirectorService;
import com.example.rentavehicleagency.employees.services.EmployeeService;
import com.example.rentavehicleagency.vehicleImages.services.VehicleImageService;
import com.example.rentavehicleagency.reports.services.ReportService;
import com.example.rentavehicleagency.requests.services.RequestService;
import com.example.rentavehicleagency.users.services.UserService;
import com.example.rentavehicleagency.vehicles.services.VehicleService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private VehicleService vehicleService;
	
	@Autowired
	private VehicleImageService vehicleImageService;
	
	@Autowired
	private RequestService requestService;
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private BusinessService businessService;
	
	@Autowired
	private DirectorService directorService;
	
	@Autowired
	private ReportService reportService;
	
	@GetMapping("/register")
	public String registerPage(Model model) {
		model.addAttribute("user", new UserEntity());
		return "registration";
	}
	
	@PostMapping("/register")
	public String submitRegisterForm(@ModelAttribute("user") UserEntity userEntity){
		userService.registerUser(userEntity);
		return "redirect:/login";
	}
	
	@GetMapping("/login")
	public String loginPage() {
		return "login";
	}
	
	@GetMapping("/user-page")
	public String userPage(Model model, Principal principal) {
		UserEntity userEntity =userService.getUserByEmail(principal.getName());
		List<VehicleEntity> allVehicleEntities =vehicleService.getAllVehicles();
		List<VehicleImageEntity> allVehiclesImages= vehicleImageService.getAllImageVehicles();
		List<VehicleDisplayDto> allVehiclesWithImages=vehicleService.getAllVehiclesWithImages(allVehicleEntities, allVehiclesImages);
		model.addAttribute("user", userEntity);
		model.addAttribute("allVehiclesWithImages", allVehiclesWithImages);
		return "user";
	}
	
	@GetMapping("/profile/{id}")
	public String showUserProfile(@PathVariable Long id, Model model) {
		UserEntity userEntity =userService.getUserById(id);
		model.addAttribute("user", userEntity);
		ClientEntity clientEntity =clientService.findClientByUserId(userEntity.getId());
		model.addAttribute("client", clientEntity);
		return "profile";
	}
	
	@PostMapping("/profile/{id}/submit-image")
	public String submitProfileImage(@PathVariable Long id, @RequestParam("image") MultipartFile image) {
		UserEntity userEntity =userService.getUserById(id);
		userService.setProfileImage(userEntity, image);
		return "redirect:/profile/{id}";
	}
	
	@GetMapping("/owner-page")
	public String ownerPage(Model model, Principal principal) {
		UserEntity userEntity =userService.getUserByEmail(principal.getName());
		List<BusinessEntity> allBusinessEntities =businessService.getAllBusinesses();
		List<ReportEntity> allReportEntities =reportService.getAllReports();
		List<VehicleEntity> allVehicleEntities =vehicleService.getAllVehicles();
		List<VehicleImageEntity> allVehiclesImages= vehicleImageService.getAllImageVehicles();
		List<VehicleDisplayDto> allVehiclesWithImages=vehicleService.getAllVehiclesWithImages(allVehicleEntities, allVehiclesImages);
		model.addAttribute("user", userEntity);
		model.addAttribute("allBusinesses", allBusinessEntities);
		model.addAttribute("allReports", allReportEntities);
		model.addAttribute("allVehiclesWithImages", allVehiclesWithImages);
		return "owner";
	}
	
	@GetMapping("/director-page")
	public String directorPage(Model model, Principal principal) {
		UserEntity userEntity =userService.getUserByEmail(principal.getName());
		DirectorEntity directorEntity =directorService.findDirectorByUserId(userEntity.getId());
		BusinessEntity businessEntity =businessService.getBusinessById(directorEntity.getBusinessEntity().getId());
		List<VehicleEntity> allVehicleEntities =vehicleService.getAllVehiclesByBusinessId(businessEntity.getId());
		List<VehicleImageEntity> allVehiclesImages= vehicleImageService.getAllImageVehicles();
		List<VehicleDisplayDto> allVehiclesWithImages=vehicleService.getAllVehiclesWithImages(allVehicleEntities, allVehiclesImages);
		model.addAttribute("user", userEntity);
		model.addAttribute("director", directorEntity);
		model.addAttribute("business", businessEntity);
		model.addAttribute("allVehiclesWithImages", allVehiclesWithImages);
		return "director";
	}
	
	@GetMapping("/hr-page")
	public String humanResourcesPage(Model model, Principal principal) {
		UserEntity userEntity =userService.getUserByEmail(principal.getName());
		EmployeeEntity employeeEntity =employeeService.findEmployeeByUserId(userEntity.getId());
		List<EmployeeEntity> businessEmployeeEntities =employeeService.getAllEmployeesFromBusiness(employeeEntity.getBusinessEntity().getId());
		BusinessEntity businessEntity =businessService.getBusinessById(employeeEntity.getBusinessEntity().getId());
		model.addAttribute("user", userEntity);
		model.addAttribute("businessEmployees", businessEmployeeEntities);
		model.addAttribute("business", businessEntity);
		return "humanResources";
	}
	
	@GetMapping("/finance-page")
	public String financePage(Model model, Principal principal) {
		UserEntity userEntity =userService.getUserByEmail(principal.getName());
		EmployeeEntity employeeEntity =employeeService.findEmployeeByUserId(userEntity.getId());
		BusinessEntity businessEntity =businessService.getBusinessById(employeeEntity.getBusinessEntity().getId());
		List<RequestDto> allRequests=requestService.getRequestsForFinances();
		model.addAttribute("user", userEntity);
		model.addAttribute("employee", employeeEntity);
		model.addAttribute("business", businessEntity);
		model.addAttribute("allRequests", allRequests);
		return "finance";
	}
	
	@GetMapping("/rent-dashboard-page")
	public String rentDashboardPage(Model model, Principal principal) {
		UserEntity userEntity =userService.getUserByEmail(principal.getName());
		List<RequestEntity> allRequestEntities =requestService.getAllRequest();
		model.addAttribute("user", userEntity);
		model.addAttribute("allRequests", allRequestEntities);
		return "rentDashboard";
	}
	
	@GetMapping("/maintenance-dashboard-page")
	public String maintenanceDashboardPage(Model model, Principal principal) {
		UserEntity userEntity =userService.getUserByEmail(principal.getName());
		EmployeeEntity employeeEntity =employeeService.findEmployeeByUserId(userEntity.getId());
		BusinessEntity businessEntity =businessService.getBusinessById(employeeEntity.getBusinessEntity().getId());
		List<VehicleEntity> allVehicleEntities =vehicleService.getAllVehicles();
		List<VehicleImageEntity> allVehiclesImages= vehicleImageService.getAllImageVehicles();
		List<VehicleDisplayDto> allVehiclesWithImages=vehicleService.getAllVehiclesWithImages(allVehicleEntities, allVehiclesImages);
		model.addAttribute("user", userEntity);
		model.addAttribute("business", businessEntity);
		model.addAttribute("allVehiclesWithImages", allVehiclesWithImages);
		return "maintenanceDashboard";
	}
	
	@GetMapping("/home-page")
	public String homePage(Model model) {
		List<VehicleEntity> allVehicleEntities =vehicleService.getAllVehicles();
		List<VehicleImageEntity> allVehiclesImages= vehicleImageService.getAllImageVehicles();
		List<VehicleDisplayDto> allVehiclesWithImages=vehicleService.getAllVehiclesWithImages(allVehicleEntities, allVehiclesImages);
		model.addAttribute("allVehiclesWithImages", allVehiclesWithImages);
		return "home";
	}
}
