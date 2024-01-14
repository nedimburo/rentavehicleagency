package com.example.rentavehicleagency.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.rentavehicleagency.models.Vehicle;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long>{
	Vehicle findByRegistrationPlate(String registrationPlate);
	List<Vehicle> findByBusinessId(Long businessId);
}
