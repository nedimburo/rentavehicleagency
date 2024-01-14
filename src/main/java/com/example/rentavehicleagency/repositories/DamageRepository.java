package com.example.rentavehicleagency.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.rentavehicleagency.models.Damage;

@Repository
public interface DamageRepository extends JpaRepository<Damage, Long>{
	List<Damage> findByVehicleId(Long vehicleId);
	List<Damage> findByEmployeeId(Long employeeId);
	void deleteById(Long damageId);
}
