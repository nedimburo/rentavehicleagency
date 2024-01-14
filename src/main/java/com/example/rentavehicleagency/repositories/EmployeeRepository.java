package com.example.rentavehicleagency.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.rentavehicleagency.models.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{
	Employee findByUserId(Long userId);
	List<Employee> findByBusinessId(Long businessId);
	void deleteById(Long employeeId);
}
