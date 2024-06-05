package com.example.rentavehicleagency.damages.repositories;

import java.util.List;

import com.example.rentavehicleagency.damages.entities.DamageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DamageRepository extends JpaRepository<DamageEntity, Long>{
	List<DamageEntity> findByVehicleEntityId(Long vehicleEntityId);
	List<DamageEntity> findByEmployeeEntityId(Long employeeEntityId);
	void deleteById(Long damageId);
}
