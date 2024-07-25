package com.example.rentavehicleagency.directors.controllers;

import java.util.List;

import com.example.rentavehicleagency.businesses.entities.BusinessEntity;
import com.example.rentavehicleagency.directors.entities.DirectorEntity;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.rentavehicleagency.directors.payloads.DirectorDto;
import com.example.rentavehicleagency.users.entities.UserEntity;
import com.example.rentavehicleagency.businesses.services.BusinessService;
import com.example.rentavehicleagency.directors.services.DirectorService;
import com.example.rentavehicleagency.users.services.UserService;

@Slf4j
@Getter
@RestController
@RequiredArgsConstructor
@RequestMapping("public/director")
@Tags(value = {@Tag(name = "Public | Director"), @Tag(name = "operationIdNamePublicDirector")})
public class DirectorController {

	private final DirectorService directorService;

	private final BusinessService businessService;
	
	private final UserService userService;
	
	@GetMapping("/add-director")
	public String addDirectorPage(Model model) {
		List<BusinessEntity> allBusinessEntities =businessService.getAllBusinesses();
		model.addAttribute("directorDto", new DirectorDto());
		model.addAttribute("allBusinesses", allBusinessEntities);
		return "addDirector";
	}
	
	@PostMapping("/add-director-submit")
	public String submitDirector(@ModelAttribute("directorDto") DirectorDto directorDto) {
		UserEntity userEntity =new UserEntity();
		userEntity.setFirstName(directorDto.getFirstName());
		userEntity.setLastName(directorDto.getLastName());
		userEntity.setEmail(directorDto.getEmail());
		userEntity.setPassword(directorDto.getPassword());
		userEntity.setNickname(directorDto.getNickname());
		userEntity.setRole("DIRECTOR");
		userEntity.setGender(directorDto.getGender());
		userEntity.setBirthDate(directorDto.getBirthDate());
		userEntity.setProfileImage("default_user_image.jpg");
		userService.saveUserInstance(userEntity);
		BusinessEntity businessEntity =businessService.findBusinessByName(directorDto.getName());
		UserEntity newRegisteredUserEntity =userService.getUserByEmail(directorDto.getEmail());
		DirectorEntity newDirectorEntity =new DirectorEntity();
		newDirectorEntity.setJmbg(directorDto.getJmbg());
		newDirectorEntity.setPay(directorDto.getPay());
		newDirectorEntity.setUserEntity(newRegisteredUserEntity);
		newDirectorEntity.setBusinessEntity(businessEntity);
		directorService.saveDirector(newDirectorEntity);
		return "redirect:/owner-page";
	}
}
