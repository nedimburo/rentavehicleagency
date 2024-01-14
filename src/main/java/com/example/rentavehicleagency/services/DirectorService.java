package com.example.rentavehicleagency.services;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.rentavehicleagency.models.Director;
import com.example.rentavehicleagency.repositories.DirectorRepository;

@Service
public class DirectorService {

	@Autowired
	private DirectorRepository directorRepository;
	
	public void saveDirector(Director director) {
		director.setHireDate(LocalDate.now());
		directorRepository.save(director);
	}
	
	public Director findDirectorByUserId(Long userId) {
		return directorRepository.findByUserId(userId);
	}
	
	public Director findDirectorByBusinessId(Long businessId) {
		return directorRepository.findByBusinessId(businessId);
	}
}
