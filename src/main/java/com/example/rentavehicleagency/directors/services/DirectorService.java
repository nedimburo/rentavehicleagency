package com.example.rentavehicleagency.directors.services;

import java.time.LocalDate;

import com.example.rentavehicleagency.directors.entities.DirectorEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.rentavehicleagency.directors.repositories.DirectorRepository;

@Service
public class DirectorService {

	@Autowired
	private DirectorRepository directorRepository;
	
	public void saveDirector(DirectorEntity directorEntity) {
		directorEntity.setHireDate(LocalDate.now());
		directorRepository.save(directorEntity);
	}
	
	public DirectorEntity findDirectorByUserId(Long userId) {
		return directorRepository.findByUserEntityId(userId);
	}
	
	public DirectorEntity findDirectorByBusinessId(Long businessId) {
		return directorRepository.findByBusinessEntityId(businessId);
	}
}
