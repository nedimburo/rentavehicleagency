package com.example.rentavehicleagency.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.rentavehicleagency.models.Business;

@Repository
public interface BusinessRepository extends JpaRepository<Business, Long>{
	Business findByName(String name);
}
