package com.example.rentavehicleagency.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.rentavehicleagency.models.ImageVehicle;

@Repository
public interface ImageVehicleRepository extends JpaRepository<ImageVehicle, Long>{
	List<ImageVehicle> findByVehicleId(Long vehicleId);
	void deleteById(Long id);
}
