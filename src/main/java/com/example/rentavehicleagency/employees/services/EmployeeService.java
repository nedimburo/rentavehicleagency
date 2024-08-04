package com.example.rentavehicleagency.employees.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.example.rentavehicleagency.businesses.entities.BusinessEntity;
import com.example.rentavehicleagency.businesses.services.BusinessService;
import com.example.rentavehicleagency.employees.Employee;
import com.example.rentavehicleagency.employees.entities.EmployeeEntity;
import com.example.rentavehicleagency.employees.payloads.EmployeeRequestDto;
import com.example.rentavehicleagency.users.entities.UserEntity;
import com.example.rentavehicleagency.users.services.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.rentavehicleagency.employees.repositories.EmployeeRepository;

@Slf4j
@Getter
@Service
@RequiredArgsConstructor
public class EmployeeService implements Employee {
	
	private final EmployeeRepository repository;
	private final UserService userService;
	private final BusinessService businessService;

	@Transactional
	public ResponseEntity<?> addEmployee(EmployeeRequestDto employeeRequestDto) {
		UserEntity userEntity = new UserEntity();
		userEntity.setFirstName(employeeRequestDto.getFirstName());
		userEntity.setLastName(employeeRequestDto.getLastName());
		userEntity.setEmail(employeeRequestDto.getEmail());
		userEntity.setPassword(employeeRequestDto.getPassword());
		userEntity.setNickname(employeeRequestDto.getNickname());
		userEntity.setRole(employeeRequestDto.getRole());
		userEntity.setGender(employeeRequestDto.getGender());
		userEntity.setBirthDate(LocalDate.parse(employeeRequestDto.getBirthDate()));
		userEntity.setProfileImage("default_user_image.jpg");
		userService.saveUserInstance(userEntity);
		BusinessEntity businessEntity = businessService.findBusinessByName(employeeRequestDto.getBusinessName());
		UserEntity newRegisteredUserEntity = userService.getUserByEmail(employeeRequestDto.getEmail());
		EmployeeEntity employee = new EmployeeEntity();
		employee.setJmbg(employeeRequestDto.getJmbg());
		employee.setPay(employeeRequestDto.getPay());
		employee.setAddress(employeeRequestDto.getAddress());
		employee.setCity(employeeRequestDto.getCity());
		employee.setContactNumber(employeeRequestDto.getContactNumber());
		employee.setEmploymentStatus(employeeRequestDto.getEmploymentStatus());
		employee.setUserEntity(newRegisteredUserEntity);
		employee.setBusinessEntity(businessEntity);
		employee.setHireDate(LocalDate.now());
		repository.save(employee);
		return new ResponseEntity<>("Employee has been successfully added.", HttpStatus.OK);
	}
	
	public EmployeeEntity findEmployeeByUserId(Long userId) {
		return repository.findByUserEntityId(userId);
	}
	
	public List<EmployeeEntity> getAllEmployeesFromBusiness(Long businessId){
		return repository.findByBusinessEntityId(businessId);
	}

	public EmployeeEntity findById(Long id){
		Optional<EmployeeEntity> employee = repository.findById(id);
		return employee.orElseThrow(() -> new EntityNotFoundException("Employee not found"));
	}
}
