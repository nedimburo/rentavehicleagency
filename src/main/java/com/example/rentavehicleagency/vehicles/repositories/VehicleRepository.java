package com.example.rentavehicleagency.vehicles.repositories;

import java.util.List;

import com.example.rentavehicleagency.vehicles.entities.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<VehicleEntity, Long>{
	VehicleEntity findByRegistrationPlate(String registrationPlate);
	List<VehicleEntity> findByBusinessEntityId(Long businessEntityId);
}
