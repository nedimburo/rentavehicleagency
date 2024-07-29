package com.example.rentavehicleagency.employees.services;

import java.time.LocalDate;
import java.util.List;

import com.example.rentavehicleagency.employees.Employee;
import com.example.rentavehicleagency.employees.entities.EmployeeEntity;
import com.example.rentavehicleagency.users.services.UserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.example.rentavehicleagency.employees.repositories.EmployeeRepository;

@Slf4j
@Getter
@Service
@RequiredArgsConstructor
public class EmployeeService implements Employee {
	
	private final EmployeeRepository repository;
	
	private final UserService userService;
	
	public void setEmployee(EmployeeEntity employeeEntity) {
		employeeEntity.setHireDate(LocalDate.now());
		repository.save(employeeEntity);
	}
	
	public EmployeeEntity findEmployeeByUserId(Long userId) {
		return repository.findByUserEntityId(userId);
	}
	
	public List<EmployeeEntity> getAllEmployeesFromBusiness(Long businessId){
		return repository.findByBusinessEntityId(businessId);
	}
}
