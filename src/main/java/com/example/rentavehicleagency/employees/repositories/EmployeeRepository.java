package com.example.rentavehicleagency.employees.repositories;

import java.util.List;

import com.example.rentavehicleagency.employees.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long>{
	EmployeeEntity findByUserEntityId(Long userEntityId);
	List<EmployeeEntity> findByBusinessEntityId(Long businessEntityId);
	void deleteById(Long employeeId);
}
