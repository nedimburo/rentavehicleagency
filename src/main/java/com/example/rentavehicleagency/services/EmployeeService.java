package com.example.rentavehicleagency.services;

import java.time.LocalDate;
import java.util.List;

import com.example.rentavehicleagency.damages.entities.DamageEntity;
import com.example.rentavehicleagency.damages.services.DamageService;
import com.example.rentavehicleagency.users.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.rentavehicleagency.models.Employee;
import com.example.rentavehicleagency.models.Report;
import com.example.rentavehicleagency.models.Request;
import com.example.rentavehicleagency.repositories.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private DamageService damageService;
	
	@Autowired
	private ReportService reportService;

	@Autowired
	private RequestService requestService;
	
	public void setEmployee(Employee employee) {
		employee.setHireDate(LocalDate.now());
		employeeRepository.save(employee);
	}
	
	public Employee findEmployeeByUserId(Long userId) {
		return employeeRepository.findByUserId(userId);
	}
	
	public List<Employee> getAllEmployeesFromBusiness(Long businessId){
		return employeeRepository.findByBusinessId(businessId);
	}
	
	public void fireEmployee(Long id) {
		Employee employee=employeeRepository.findById(id).orElse(null);
		List<DamageEntity> damagesEmployee=damageService.getDamagesByEmployeeId(employee.getId());
		List<Report> reportsEmployee=reportService.findReportsByEmployeeId(employee.getId());
		List<Request> requestsEmployee=requestService.findRequestsByEmployeeId(employee.getId());
		for (int i=0; i<damagesEmployee.size(); i++) {
			damageService.deleteDamageById(damagesEmployee.get(i).getId());
		}
		for (int i=0; i<reportsEmployee.size(); i++) {
			reportService.deleteReportById(reportsEmployee.get(i).getId());
		}
		for (int i=0; i<requestsEmployee.size(); i++) {
			requestService.deleteRequestById(requestsEmployee.get(i).getId());
		}
		employeeRepository.deleteById(id);
		userService.deleteUserById(employee.getUserEntity().getId());
	}
}
