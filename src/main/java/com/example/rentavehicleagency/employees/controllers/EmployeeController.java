package com.example.rentavehicleagency.employees.controllers;

import com.example.rentavehicleagency.businesses.entities.BusinessEntity;
import com.example.rentavehicleagency.employees.entities.EmployeeEntity;
import com.example.rentavehicleagency.users.entities.UserEntity;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import com.example.rentavehicleagency.employees.payloads.EmployeeDto;
import com.example.rentavehicleagency.businesses.services.BusinessService;
import com.example.rentavehicleagency.employees.services.EmployeeService;
import com.example.rentavehicleagency.users.services.UserService;

@Slf4j
@Getter
@RestController
@RequiredArgsConstructor
@RequestMapping("public/employee")
@Tags(value = @Tag(name = "Public | Employee"))
public class EmployeeController {

	private final EmployeeService employeeService;

	private final BusinessService businessService;
	
	private final UserService userService;
	
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
