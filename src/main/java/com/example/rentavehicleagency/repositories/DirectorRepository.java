package com.example.rentavehicleagency.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.rentavehicleagency.models.Director;

@Repository
public interface DirectorRepository extends JpaRepository<Director, Long>{
	Director findByUserId(Long userId);
	Director findByBusinessId(Long businessId);
}
