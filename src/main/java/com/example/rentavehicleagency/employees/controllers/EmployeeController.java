package com.example.rentavehicleagency.employees.controllers;

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

import com.example.rentavehicleagency.employees.payloads.EmployeeDto;
import com.example.rentavehicleagency.businesses.services.BusinessService;
import com.example.rentavehicleagency.employees.services.EmployeeService;
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
		EmployeeEntity newEmployeeEntity =new EmployeeEntity();
		newEmployeeEntity.setJmbg(employeeDto.getJmbg());
		newEmployeeEntity.setPay(employeeDto.getPay());
		newEmployeeEntity.setAddress(employeeDto.getAddress());
		newEmployeeEntity.setCity(employeeDto.getCity());
		newEmployeeEntity.setContactNumber(employeeDto.getContactNumber());
		newEmployeeEntity.setEmploymentStatus(employeeDto.getEmploymentStatus());
		newEmployeeEntity.setUserEntity(newRegisteredUserEntity);
		newEmployeeEntity.setBusinessEntity(businessEntity);
		employeeService.setEmployee(newEmployeeEntity);
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
		EmployeeEntity newEmployeeEntity =new EmployeeEntity();
		newEmployeeEntity.setJmbg(employeeDto.getJmbg());
		newEmployeeEntity.setPay(employeeDto.getPay());
		newEmployeeEntity.setAddress(employeeDto.getAddress());
		newEmployeeEntity.setCity(employeeDto.getCity());
		newEmployeeEntity.setContactNumber(employeeDto.getContactNumber());
		newEmployeeEntity.setEmploymentStatus(employeeDto.getEmploymentStatus());
		newEmployeeEntity.setUserEntity(newRegisteredUserEntity);
		newEmployeeEntity.setBusinessEntity(businessEntity);
		employeeService.setEmployee(newEmployeeEntity);
		return "redirect:/hr-page";
	}
	
	@GetMapping("/fire-employee/{id}")
	public String fireEmployee(@PathVariable Long id) {
		employeeService.fireEmployee(id);
		return "redirect:/hr-page";
	}
}
