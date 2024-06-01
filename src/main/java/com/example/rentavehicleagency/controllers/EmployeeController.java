package com.example.rentavehicleagency.controllers;

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

import com.example.rentavehicleagency.dto.EmployeeDto;
import com.example.rentavehicleagency.models.Employee;
import com.example.rentavehicleagency.businesses.services.BusinessService;
import com.example.rentavehicleagency.services.EmployeeService;
import com.example.rentavehicleagency.users.services.UserService;

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
		List<BusinessEntity> allBusinessEntities =businessService.getAllBusinesses();
		model.addAttribute("employeeDto", new EmployeeDto());
		model.addAttribute("allBusinesses", allBusinessEntities);
		return "addEmployee";
	}
	
	@PostMapping("/add-employee-submit")
	public String submitEmployee(@ModelAttribute("employeeDto") EmployeeDto employeeDto) {
		UserEntity userEntity =new UserEntity();
		userEntity.setFirstName(employeeDto.getFirstName());
		userEntity.setLastName(employeeDto.getLastName());
		userEntity.setEmail(employeeDto.getEmail());
		userEntity.setPassword(employeeDto.getPassword());
		userEntity.setNickname(employeeDto.getNickname());
		userEntity.setRole(employeeDto.getRole());
		userEntity.setGender(employeeDto.getGender());
		userEntity.setBirthDate(employeeDto.getBirthDate());
		userEntity.setProfileImage("default_user_image.jpg");
		userService.saveUserInstance(userEntity);
		BusinessEntity businessEntity =businessService.findBusinessByName(employeeDto.getName());
		UserEntity newRegisteredUserEntity =userService.getUserByEmail(employeeDto.getEmail());
		Employee newEmployee=new Employee();
		newEmployee.setJmbg(employeeDto.getJmbg());
		newEmployee.setPay(employeeDto.getPay());
		newEmployee.setAddress(employeeDto.getAddress());
		newEmployee.setCity(employeeDto.getCity());
		newEmployee.setContactNumber(employeeDto.getContactNumber());
		newEmployee.setEmploymentStatus(employeeDto.getEmploymentStatus());
		newEmployee.setUserEntity(newRegisteredUserEntity);
		newEmployee.setBusinessEntity(businessEntity);
		employeeService.setEmployee(newEmployee);
		return "redirect:/owner-page";
	}
	
	@GetMapping("/add-employee-hr")
	public String addEmployeeHRPage(Model model) {
		List<BusinessEntity> allBusinessEntities =businessService.getAllBusinesses();
		model.addAttribute("employeeDto", new EmployeeDto());
		model.addAttribute("allBusinesses", allBusinessEntities);
		return "addEmployeeHR";
	}
	
	@PostMapping("/add-employee-hr-submit")
	public String submitEmployeeHR(@ModelAttribute("employeeDto") EmployeeDto employeeDto) {
		UserEntity userEntity =new UserEntity();
		userEntity.setFirstName(employeeDto.getFirstName());
		userEntity.setLastName(employeeDto.getLastName());
		userEntity.setEmail(employeeDto.getEmail());
		userEntity.setPassword(employeeDto.getPassword());
		userEntity.setNickname(employeeDto.getNickname());
		userEntity.setRole(employeeDto.getRole());
		userEntity.setGender(employeeDto.getGender());
		userEntity.setBirthDate(employeeDto.getBirthDate());
		userEntity.setProfileImage("default_user_image.jpg");
		userService.saveUserInstance(userEntity);
		BusinessEntity businessEntity =businessService.findBusinessByName(employeeDto.getName());
		UserEntity newRegisteredUserEntity =userService.getUserByEmail(employeeDto.getEmail());
		Employee newEmployee=new Employee();
		newEmployee.setJmbg(employeeDto.getJmbg());
		newEmployee.setPay(employeeDto.getPay());
		newEmployee.setAddress(employeeDto.getAddress());
		newEmployee.setCity(employeeDto.getCity());
		newEmployee.setContactNumber(employeeDto.getContactNumber());
		newEmployee.setEmploymentStatus(employeeDto.getEmploymentStatus());
		newEmployee.setUserEntity(newRegisteredUserEntity);
		newEmployee.setBusinessEntity(businessEntity);
		employeeService.setEmployee(newEmployee);
		return "redirect:/hr-page";
	}
	
	@GetMapping("/fire-employee/{id}")
	public String fireEmployee(@PathVariable Long id) {
		employeeService.fireEmployee(id);
		return "redirect:/hr-page";
	}
}
