package com.example.rentavehicleagency.businesses.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.rentavehicleagency.businesses.entities.BusinessEntity;

@Repository
public interface BusinessRepository extends JpaRepository<BusinessEntity, Long>{
	BusinessEntity findByName(String name);
}
