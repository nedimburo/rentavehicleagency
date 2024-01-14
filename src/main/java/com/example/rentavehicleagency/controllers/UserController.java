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
import org.springframework.web.multipart.MultipartFile;

import com.example.rentavehicleagency.dto.RequestDto;
import com.example.rentavehicleagency.dto.VehicleDisplayDto;
import com.example.rentavehicleagency.models.Business;
import com.example.rentavehicleagency.models.Client;
import com.example.rentavehicleagency.models.Director;
import com.example.rentavehicleagency.models.Employee;
import com.example.rentavehicleagency.models.ImageVehicle;
import com.example.rentavehicleagency.models.Report;
import com.example.rentavehicleagency.models.Request;
import com.example.rentavehicleagency.models.User;
import com.example.rentavehicleagency.models.Vehicle;
import com.example.rentavehicleagency.services.BusinessService;
import com.example.rentavehicleagency.services.ClientService;
import com.example.rentavehicleagency.services.DirectorService;
import com.example.rentavehicleagency.services.EmployeeService;
import com.example.rentavehicleagency.services.ImageVehicleService;
import com.example.rentavehicleagency.services.ReportService;
import com.example.rentavehicleagency.services.RequestService;
import com.example.rentavehicleagency.services.UserService;
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
		model.addAttribute("user", new User());
		return "registration";
	}
	
	@PostMapping("/register")
	public String submitRegisterForm(@ModelAttribute("user") User user){
		userService.registerUser(user);
		return "redirect:/login";
	}
	
	@GetMapping("/login")
	public String loginPage() {
		return "login";
	}
	
	@GetMapping("/user-page")
	public String userPage(Model model, Principal principal) {
		User user=userService.getUserByEmail(principal.getName());
		List<Vehicle> allVehicles=vehicleService.getAllVehicles();
		List<ImageVehicle> allVehiclesImages=imageVehicleService.getAllImageVehicles();
		List<VehicleDisplayDto> allVehiclesWithImages=vehicleService.getAllVehiclesWithImages(allVehicles, allVehiclesImages);
		model.addAttribute("user", user);
		model.addAttribute("allVehiclesWithImages", allVehiclesWithImages);
		return "user";
	}
	
	@GetMapping("/profile/{id}")
	public String showUserProfile(@PathVariable Long id, Model model) {
		User user=userService.getUserById(id);
		model.addAttribute("user", user);
		Client client=clientService.findClientByUserId(user.getId());
		model.addAttribute("client", client);
		return "profile";
	}
	
	@PostMapping("/profile/{id}/submit-image")
	public String submitProfileImage(@PathVariable Long id, @RequestParam("image") MultipartFile image) {
		User user=userService.getUserById(id);
		userService.setProfileImage(user, image);
		return "redirect:/profile/{id}";
	}
	
	@GetMapping("/owner-page")
	public String ownerPage(Model model, Principal principal) {
		User user=userService.getUserByEmail(principal.getName());
		List<Business> allBusinesses=businessService.getAllBusinesses();
		List<Report> allReports=reportService.getAllReports();
		List<Vehicle> allVehicles=vehicleService.getAllVehicles();
		List<ImageVehicle> allVehiclesImages=imageVehicleService.getAllImageVehicles();
		List<VehicleDisplayDto> allVehiclesWithImages=vehicleService.getAllVehiclesWithImages(allVehicles, allVehiclesImages);
		model.addAttribute("user", user);
		model.addAttribute("allBusinesses", allBusinesses);
		model.addAttribute("allReports", allReports);
		model.addAttribute("allVehiclesWithImages", allVehiclesWithImages);
		return "owner";
	}
	
	@GetMapping("/director-page")
	public String directorPage(Model model, Principal principal) {
		User user=userService.getUserByEmail(principal.getName());
		Director director=directorService.findDirectorByUserId(user.getId());
		Business business=businessService.getBusinessById(director.getBusiness().getId());
		List<Vehicle> allVehicles=vehicleService.getAllVehiclesByBusinessId(business.getId());
		List<ImageVehicle> allVehiclesImages=imageVehicleService.getAllImageVehicles();
		List<VehicleDisplayDto> allVehiclesWithImages=vehicleService.getAllVehiclesWithImages(allVehicles, allVehiclesImages);
		model.addAttribute("user", user);
		model.addAttribute("director", director);
		model.addAttribute("business", business);
		model.addAttribute("allVehiclesWithImages", allVehiclesWithImages);
		return "director";
	}
	
	@GetMapping("/hr-page")
	public String humanResourcesPage(Model model, Principal principal) {
		User user=userService.getUserByEmail(principal.getName());
		Employee employee=employeeService.findEmployeeByUserId(user.getId());
		List<Employee> businessEmployees=employeeService.getAllEmployeesFromBusiness(employee.getBusiness().getId());
		Business business=businessService.getBusinessById(employee.getBusiness().getId());
		model.addAttribute("user", user);
		model.addAttribute("businessEmployees", businessEmployees);
		model.addAttribute("business", business);
		return "humanResources";
	}
	
	@GetMapping("/finance-page")
	public String financePage(Model model, Principal principal) {
		User user=userService.getUserByEmail(principal.getName());
		Employee employee=employeeService.findEmployeeByUserId(user.getId());
		Business business=businessService.getBusinessById(employee.getBusiness().getId());
		List<RequestDto> allRequests=requestService.getRequestsForFinances();
		model.addAttribute("user", user);
		model.addAttribute("employee", employee);
		model.addAttribute("business", business);
		model.addAttribute("allRequests", allRequests);
		return "finance";
	}
	
	@GetMapping("/rent-dashboard-page")
	public String rentDashboardPage(Model model, Principal principal) {
		User user=userService.getUserByEmail(principal.getName());
		List<Request> allRequests=requestService.getAllRequest();
		model.addAttribute("user", user);
		model.addAttribute("allRequests", allRequests);
		return "rentDashboard";
	}
	
	@GetMapping("/maintenance-dashboard-page")
	public String maintenanceDashboardPage(Model model, Principal principal) {
		User user=userService.getUserByEmail(principal.getName());
		Employee employee=employeeService.findEmployeeByUserId(user.getId());
		Business business=businessService.getBusinessById(employee.getBusiness().getId());
		List<Vehicle> allVehicles=vehicleService.getAllVehicles();
		List<ImageVehicle> allVehiclesImages=imageVehicleService.getAllImageVehicles();
		List<VehicleDisplayDto> allVehiclesWithImages=vehicleService.getAllVehiclesWithImages(allVehicles, allVehiclesImages);
		model.addAttribute("user", user);
		model.addAttribute("business", business);
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
