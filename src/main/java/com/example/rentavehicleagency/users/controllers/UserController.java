package com.example.rentavehicleagency.users.controllers;

import com.example.rentavehicleagency.users.entities.UserEntity;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.rentavehicleagency.users.services.UserService;

@Slf4j
@Getter
@RestController
@RequiredArgsConstructor
@RequestMapping("public/user")
@Tags(value = @Tag(name = "Public | User"))
public class UserController {

	private final UserService service;
	
	@PostMapping("/profile/{id}/submit-image")
	public String submitProfileImage(@PathVariable Long id, @RequestParam("image") MultipartFile image) {
		UserEntity userEntity = service.getUserById(id);
		service.setProfileImage(userEntity, image);
		return "redirect:/profile/{id}";
	}

}
