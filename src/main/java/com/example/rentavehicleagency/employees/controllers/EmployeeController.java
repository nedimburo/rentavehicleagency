package com.example.rentavehicleagency.employees.controllers;

import com.example.rentavehicleagency.employees.services.RemoveEmployeeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.rentavehicleagency.employees.payloads.EmployeeRequestDto;
import com.example.rentavehicleagency.employees.services.EmployeeService;

@Slf4j
@Getter
@RestController
@RequiredArgsConstructor
@RequestMapping("public/employee")
@Tags(value = @Tag(name = "Public | Employee"))
public class EmployeeController {

	private final EmployeeService service;
	private final RemoveEmployeeService removeEmployeeService;
	
	@PostMapping("/add-employee-submit")
	public ResponseEntity<?> addEmployee(@ModelAttribute("employeeDto") EmployeeRequestDto employeeRequestDto) {
		return service.addEmployee(employeeRequestDto);
	}

	@DeleteMapping("/fire-employee/{id}")
	public ResponseEntity<?> fireEmployee(@PathVariable Long id){
		return removeEmployeeService.fireEmployee(id);
	}
}
