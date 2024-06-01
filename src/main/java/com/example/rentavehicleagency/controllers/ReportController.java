package com.example.rentavehicleagency.controllers;

import java.security.Principal;

import com.example.rentavehicleagency.users.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.rentavehicleagency.models.Employee;
import com.example.rentavehicleagency.models.Report;
import com.example.rentavehicleagency.services.EmployeeService;
import com.example.rentavehicleagency.services.ReportService;
import com.example.rentavehicleagency.users.services.UserService;

@Controller
public class ReportController {

	@Autowired
	private ReportService reportService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/add-report")
	private String addReportPage(Model model) {
		model.addAttribute("report", new Report());
		return "addReport";
	}
	
	@PostMapping("/add-report-submit")
	private String submitReport(@ModelAttribute("report") Report report, Principal principal) {
		UserEntity userEntity =userService.getUserByEmail(principal.getName());
		Employee employee=employeeService.findEmployeeByUserId(userEntity.getId());
		report.setEmployee(employee);
		reportService.saveReport(report);
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
