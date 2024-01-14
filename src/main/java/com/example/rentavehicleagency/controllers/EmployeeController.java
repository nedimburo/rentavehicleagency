package com.example.rentavehicleagency.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.rentavehicleagency.dto.EmployeeDto;
import com.example.rentavehicleagency.models.Business;
import com.example.rentavehicleagency.models.Employee;
import com.example.rentavehicleagency.models.User;
import com.example.rentavehicleagency.services.BusinessService;
import com.example.rentavehicleagency.services.EmployeeService;
import com.example.rentavehicleagency.services.UserService;

@Controller
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private BusinessService businessService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/add-employee")
	public String addEmployeePage(Model model) {
		List<Business> allBusinesses=businessService.getAllBusinesses();
		model.addAttribute("employeeDto", new EmployeeDto());
		model.addAttribute("allBusinesses", allBusinesses);
		return "addEmployee";
	}
	
	@PostMapping("/add-employee-submit")
	public String submitEmployee(@ModelAttribute("employeeDto") EmployeeDto employeeDto) {
		User user=new User(employeeDto.getFirstName(), employeeDto.getLastName(), employeeDto.getEmail(),
				employeeDto.getPassword(), employeeDto.getNickname(), employeeDto.getRole(), employeeDto.getGender(),
				employeeDto.getBirthDate(), "default_user_image.jpg");
		userService.saveUserInstance(user);
		Business business=businessService.findBusinessByName(employeeDto.getName());
		User newRegisteredUser=userService.getUserByEmail(employeeDto.getEmail());
		Employee newEmployee=new Employee();
		newEmployee.setJmbg(employeeDto.getJmbg());
		newEmployee.setPay(employeeDto.getPay());
		newEmployee.setAddress(employeeDto.getAddress());
		newEmployee.setCity(employeeDto.getCity());
		newEmployee.setContactNumber(employeeDto.getContactNumber());
		newEmployee.setEmploymentStatus(employeeDto.getEmploymentStatus());
		newEmployee.setUser(newRegisteredUser);
		newEmployee.setBusiness(business);
		employeeService.setEmployee(newEmployee);
		return "redirect:/owner-page";
	}
	
	@GetMapping("/add-employee-hr")
	public String addEmployeeHRPage(Model model) {
		List<Business> allBusinesses=businessService.getAllBusinesses();
		model.addAttribute("employeeDto", new EmployeeDto());
		model.addAttribute("allBusinesses", allBusinesses);
		return "addEmployeeHR";
	}
	
	@PostMapping("/add-employee-hr-submit")
	public String submitEmployeeHR(@ModelAttribute("employeeDto") EmployeeDto employeeDto) {
		User user=new User(employeeDto.getFirstName(), employeeDto.getLastName(), employeeDto.getEmail(),
				employeeDto.getPassword(), employeeDto.getNickname(), employeeDto.getRole(), employeeDto.getGender(),
				employeeDto.getBirthDate(), "default_user_image.jpg");
		userService.saveUserInstance(user);
		Business business=businessService.findBusinessByName(employeeDto.getName());
		User newRegisteredUser=userService.getUserByEmail(employeeDto.getEmail());
		Employee newEmployee=new Employee();
		newEmployee.setJmbg(employeeDto.getJmbg());
		newEmployee.setPay(employeeDto.getPay());
		newEmployee.setAddress(employeeDto.getAddress());
		newEmployee.setCity(employeeDto.getCity());
		newEmployee.setContactNumber(employeeDto.getContactNumber());
		newEmployee.setEmploymentStatus(employeeDto.getEmploymentStatus());
		newEmployee.setUser(newRegisteredUser);
		newEmployee.setBusiness(business);
		employeeService.setEmployee(newEmployee);
		return "redirect:/hr-page";
	}
	
	@GetMapping("/fire-employee/{id}")
	public String fireEmployee(@PathVariable Long id) {
		employeeService.fireEmployee(id);
		return "redirect:/hr-page";
	}
}
