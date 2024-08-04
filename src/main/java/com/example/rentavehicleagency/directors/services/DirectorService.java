package com.example.rentavehicleagency.directors.services;

import java.time.LocalDate;

import com.example.rentavehicleagency.businesses.entities.BusinessEntity;
import com.example.rentavehicleagency.businesses.services.BusinessService;
import com.example.rentavehicleagency.directors.Director;
import com.example.rentavehicleagency.directors.entities.DirectorEntity;
import com.example.rentavehicleagency.directors.payloads.DirectorRequestDto;
import com.example.rentavehicleagency.users.entities.RoleType;
import com.example.rentavehicleagency.users.entities.UserEntity;
import com.example.rentavehicleagency.users.services.UserService;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.rentavehicleagency.directors.repositories.DirectorRepository;

@Slf4j
@Getter
@Service
@RequiredArgsConstructor
public class DirectorService implements Director {

	private final DirectorRepository repository;
	private final UserService userService;
	private final BusinessService businessService;

	@Transactional
	public ResponseEntity<?> addDirector(DirectorRequestDto directorRequestDto) {
		UserEntity userEntity = new UserEntity();
		userEntity.setFirstName(directorRequestDto.getFirstName());
		userEntity.setLastName(directorRequestDto.getLastName());
		userEntity.setEmail(directorRequestDto.getEmail());
		userEntity.setPassword(directorRequestDto.getPassword());
		userEntity.setNickname(directorRequestDto.getNickname());
		userEntity.setRole(RoleType.DIRECTOR);
		userEntity.setGender(directorRequestDto.getGender());
		userEntity.setBirthDate(LocalDate.parse(directorRequestDto.getBirthDate()));
		userEntity.setProfileImage("default_user_image.jpg");
		userService.saveUserInstance(userEntity);
		BusinessEntity businessEntity = businessService.findBusinessByName(directorRequestDto.getBusinessName());
		UserEntity newRegisteredUserEntity = userService.getUserByEmail(directorRequestDto.getEmail());
		DirectorEntity director = new DirectorEntity();
		director.setJmbg(directorRequestDto.getJmbg());
		director.setPay(directorRequestDto.getPay());
		director.setUserEntity(newRegisteredUserEntity);
		director.setBusinessEntity(businessEntity);
		director.setHireDate(LocalDate.now());
		repository.save(director);
		return new ResponseEntity<>("Director has been successfully added.", HttpStatus.OK);
	}
	
	public DirectorEntity findDirectorByUserId(Long userId) {
		return repository.findByUserEntityId(userId);
	}
	
	public DirectorEntity findDirectorByBusinessId(Long businessId) {
		return repository.findByBusinessEntityId(businessId);
	}
}
