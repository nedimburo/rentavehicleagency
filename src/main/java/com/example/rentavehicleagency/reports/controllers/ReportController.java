package com.example.rentavehicleagency.reports.controllers;

import java.security.Principal;

import com.example.rentavehicleagency.employees.entities.EmployeeEntity;
import com.example.rentavehicleagency.users.entities.UserEntity;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import com.example.rentavehicleagency.reports.entities.ReportEntity;
import com.example.rentavehicleagency.employees.services.EmployeeService;
import com.example.rentavehicleagency.reports.services.ReportService;
import com.example.rentavehicleagency.users.services.UserService;

@Slf4j
@Getter
@RestController
@RequiredArgsConstructor
@RequestMapping("public/report")
@Tags(value = @Tag(name = "Public | Report"))
public class ReportController {

	private final ReportService reportService;

	private final UserService userService;

	private final EmployeeService employeeService;
	
	@PostMapping("/add-report-submit")
	private String submitReport(@ModelAttribute("report") ReportEntity reportEntity, Principal principal) {
		UserEntity userEntity =userService.getUserByEmail(principal.getName());
		EmployeeEntity employeeEntity =employeeService.findEmployeeByUserId(userEntity.getId());
		reportEntity.setEmployeeEntity(employeeEntity);
		reportService.saveReport(reportEntity);
		if (userEntity.getRole().equals("HREMPLOYEE")) {
			return "redirect:/hr-page";
		}else if(userEntity.getRole().equals("FINEMPLOYEE")) {
			return "redirect:/finance-page";
		}else if(userEntity.getRole().equals("RENTEMPLOYEE")) {
			return "redirect:/rent-dashboard-page";
		}else {
			return "redirect:/maintenance-dashboard-page";
		}
	}
	
	@GetMapping("/redirect-from-report")
	private String redirectFromReport(Principal principal) {
		UserEntity userEntity =userService.getUserByEmail(principal.getName());
		if (userEntity.getRole().equals("HREMPLOYEE")) {
			return "redirect:/hr-page";
		}else if(userEntity.getRole().equals("FINEMPLOYEE")) {
			return "redirect:/finance-page";
		}else if(userEntity.getRole().equals("RENTEMPLOYEE")) {
			return "redirect:/rent-dashboard-page";
		}else {
			return "redirect:/maintenance-dashboard-page";
		}
	}
}
