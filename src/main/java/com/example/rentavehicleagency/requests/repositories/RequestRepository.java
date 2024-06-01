package com.example.rentavehicleagency.requests.repositories;

import java.util.List;

import com.example.rentavehicleagency.requests.entities.RequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepository extends JpaRepository<RequestEntity, Long>{
	List<RequestEntity> findByClientId(Long clientId);
	List<RequestEntity> findByEmployeeId(Long employeeId);
	List<RequestEntity> findByVehicleId(Long vehicleId);
	void deleteById(Long requestId);
}
