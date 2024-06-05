package com.example.rentavehicleagency.employees.services;

import java.time.LocalDate;
import java.util.List;

import com.example.rentavehicleagency.damages.entities.DamageEntity;
import com.example.rentavehicleagency.damages.services.DamageService;
import com.example.rentavehicleagency.employees.entities.EmployeeEntity;
import com.example.rentavehicleagency.reports.entities.ReportEntity;
import com.example.rentavehicleagency.reports.services.ReportService;
import com.example.rentavehicleagency.requests.entities.RequestEntity;
import com.example.rentavehicleagency.requests.services.RequestService;
import com.example.rentavehicleagency.users.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.rentavehicleagency.employees.repositories.EmployeeRepository;

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
	
	public void setEmployee(EmployeeEntity employeeEntity) {
		employeeEntity.setHireDate(LocalDate.now());
		employeeRepository.save(employeeEntity);
	}
	
	public EmployeeEntity findEmployeeByUserId(Long userId) {
		return employeeRepository.findByUserEntityId(userId);
	}
	
	public List<EmployeeEntity> getAllEmployeesFromBusiness(Long businessId){
		return employeeRepository.findByBusinessEntityId(businessId);
	}
	
	public void fireEmployee(Long id) {
		EmployeeEntity employeeEntity =employeeRepository.findById(id).orElse(null);
		List<DamageEntity> damagesEmployee=damageService.getDamagesByEmployeeId(employeeEntity.getId());
		List<ReportEntity> reportsEmployee=reportService.findReportsByEmployeeId(employeeEntity.getId());
		List<RequestEntity> requestsEmployee=requestService.findRequestsByEmployeeId(employeeEntity.getId());
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
		userService.deleteUserById(employeeEntity.getUserEntity().getId());
	}
}
