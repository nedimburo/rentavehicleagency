package com.example.rentavehicleagency.vehicleImages.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.rentavehicleagency.vehicleImages.entities.VehicleImageEntity;

@Repository
public interface VehicleImageRepository extends JpaRepository<VehicleImageEntity, Long>{
	List<VehicleImageEntity> findByVehicleId(Long vehicleId);
	void deleteById(Long id);
}
