package com.example.rentavehicleagency.directors.repositories;

import com.example.rentavehicleagency.directors.entities.DirectorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectorRepository extends JpaRepository<DirectorEntity, Long>{
	DirectorEntity findByUserId(Long userId);
	DirectorEntity findByBusinessId(Long businessId);
}
