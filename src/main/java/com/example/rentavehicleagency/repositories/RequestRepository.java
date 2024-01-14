package com.example.rentavehicleagency.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.rentavehicleagency.models.Request;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long>{
	List<Request> findByClientId(Long clientId);
	List<Request> findByEmployeeId(Long employeeId);
	List<Request> findByVehicleId(Long vehicleId);
	void deleteById(Long requestId);
}
