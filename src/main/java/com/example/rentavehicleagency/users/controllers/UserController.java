package com.example.rentavehicleagency.users.controllers;

import java.security.Principal;
import java.util.List;

import com.example.rentavehicleagency.businesses.entities.BusinessEntity;
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

import com.example.rentavehicleagency.dto.RequestDto;
import com.example.rentavehicleagency.dto.VehicleDisplayDto;
import com.example.rentavehicleagency.clients.entities.ClientEntity;
import com.example.rentavehicleagency.directors.entities.DirectorEntity;
import com.example.rentavehicleagency.models.Employee;
import com.example.rentavehicleagency.models.ImageVehicle;
import com.example.rentavehicleagency.models.Report;
import com.example.rentavehicleagency.models.Request;
import com.example.rentavehicleagency.models.Vehicle;
import com.example.rentavehicleagency.businesses.services.BusinessService;
import com.example.rentavehicleagency.clients.services.ClientService;
import com.example.rentavehicleagency.directors.services.DirectorService;
import com.example.rentavehicleagency.services.EmployeeService;
import com.example.rentavehicleagency.services.ImageVehicleService;
import com.example.rentavehicleagency.services.ReportService;
import com.example.rentavehicleagency.services.RequestService;
import com.example.rentavehicleagency.users.services.UserService;
import com.example.rentavehicleagency.services.VehicleService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private VehicleService vehicleService;
	
	@Autowired
	private ImageVehicleService imageVehicleService;
	
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
		List<Vehicle> allVehicles=vehicleService.getAllVehicles();
		List<ImageVehicle> allVehiclesImages=imageVehicleService.getAllImageVehicles();
		List<VehicleDisplayDto> allVehiclesWithImages=vehicleService.getAllVehiclesWithImages(allVehicles, allVehiclesImages);
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
		List<Report> allReports=reportService.getAllReports();
		List<Vehicle> allVehicles=vehicleService.getAllVehicles();
		List<ImageVehicle> allVehiclesImages=imageVehicleService.getAllImageVehicles();
		List<VehicleDisplayDto> allVehiclesWithImages=vehicleService.getAllVehiclesWithImages(allVehicles, allVehiclesImages);
		model.addAttribute("user", userEntity);
		model.addAttribute("allBusinesses", allBusinessEntities);
		model.addAttribute("allReports", allReports);
		model.addAttribute("allVehiclesWithImages", allVehiclesWithImages);
		return "owner";
	}
	
	@GetMapping("/director-page")
	public String directorPage(Model model, Principal principal) {
		UserEntity userEntity =userService.getUserByEmail(principal.getName());
		DirectorEntity directorEntity =directorService.findDirectorByUserId(userEntity.getId());
		BusinessEntity businessEntity =businessService.getBusinessById(directorEntity.getBusinessEntity().getId());
		List<Vehicle> allVehicles=vehicleService.getAllVehiclesByBusinessId(businessEntity.getId());
		List<ImageVehicle> allVehiclesImages=imageVehicleService.getAllImageVehicles();
		List<VehicleDisplayDto> allVehiclesWithImages=vehicleService.getAllVehiclesWithImages(allVehicles, allVehiclesImages);
		model.addAttribute("user", userEntity);
		model.addAttribute("director", directorEntity);
		model.addAttribute("business", businessEntity);
		model.addAttribute("allVehiclesWithImages", allVehiclesWithImages);
		return "director";
	}
	
	@GetMapping("/hr-page")
	public String humanResourcesPage(Model model, Principal principal) {
		UserEntity userEntity =userService.getUserByEmail(principal.getName());
		Employee employee=employeeService.findEmployeeByUserId(userEntity.getId());
		List<Employee> businessEmployees=employeeService.getAllEmployeesFromBusiness(employee.getBusinessEntity().getId());
		BusinessEntity businessEntity =businessService.getBusinessById(employee.getBusinessEntity().getId());
		model.addAttribute("user", userEntity);
		model.addAttribute("businessEmployees", businessEmployees);
		model.addAttribute("business", businessEntity);
		return "humanResources";
	}
	
	@GetMapping("/finance-page")
	public String financePage(Model model, Principal principal) {
		UserEntity userEntity =userService.getUserByEmail(principal.getName());
		Employee employee=employeeService.findEmployeeByUserId(userEntity.getId());
		BusinessEntity businessEntity =businessService.getBusinessById(employee.getBusinessEntity().getId());
		List<RequestDto> allRequests=requestService.getRequestsForFinances();
		model.addAttribute("user", userEntity);
		model.addAttribute("employee", employee);
		model.addAttribute("business", businessEntity);
		model.addAttribute("allRequests", allRequests);
		return "finance";
	}
	
	@GetMapping("/rent-dashboard-page")
	public String rentDashboardPage(Model model, Principal principal) {
		UserEntity userEntity =userService.getUserByEmail(principal.getName());
		List<Request> allRequests=requestService.getAllRequest();
		model.addAttribute("user", userEntity);
		model.addAttribute("allRequests", allRequests);
		return "rentDashboard";
	}
	
	@GetMapping("/maintenance-dashboard-page")
	public String maintenanceDashboardPage(Model model, Principal principal) {
		UserEntity userEntity =userService.getUserByEmail(principal.getName());
		Employee employee=employeeService.findEmployeeByUserId(userEntity.getId());
		BusinessEntity businessEntity =businessService.getBusinessById(employee.getBusinessEntity().getId());
		List<Vehicle> allVehicles=vehicleService.getAllVehicles();
		List<ImageVehicle> allVehiclesImages=imageVehicleService.getAllImageVehicles();
		List<VehicleDisplayDto> allVehiclesWithImages=vehicleService.getAllVehiclesWithImages(allVehicles, allVehiclesImages);
		model.addAttribute("user", userEntity);
		model.addAttribute("business", businessEntity);
		model.addAttribute("allVehiclesWithImages", allVehiclesWithImages);
		return "maintenanceDashboard";
	}
	
	@GetMapping("/home-page")
	public String homePage(Model model) {
		List<Vehicle> allVehicles=vehicleService.getAllVehicles();
		List<ImageVehicle> allVehiclesImages=imageVehicleService.getAllImageVehicles();
		List<VehicleDisplayDto> allVehiclesWithImages=vehicleService.getAllVehiclesWithImages(allVehicles, allVehiclesImages);
		model.addAttribute("allVehiclesWithImages", allVehiclesWithImages);
		return "home";
	}
}
