package com.example.rentavehicleagency.clients.controllers;

import com.example.rentavehicleagency.clients.entities.ClientEntity;
import com.example.rentavehicleagency.users.entities.UserEntity;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.rentavehicleagency.clients.services.ClientService;
import com.example.rentavehicleagency.users.services.UserService;

@Slf4j
@Getter
@RestController
@RequiredArgsConstructor
@RequestMapping("public/client")
@Tags(value = {@Tag(name = "Public | Client"), @Tag(name = "operationIdNamePublicClient")})
public class ClientController {
	
	private final ClientService clientService;
	
	private final UserService userService;

	@GetMapping("/profile/{id}/add-information")
	public String addInformationPage(@PathVariable Long id, Model model) {
		ClientEntity clientEntity =clientService.findClientByUserId(id);
		model.addAttribute("client", clientEntity);
		return "addInformation";
	}
	
	@PostMapping("/profile/{id}/submit-information")
	public String submitInformation(@ModelAttribute("client") ClientEntity clientEntity, @PathVariable Long id) {
		UserEntity userEntity =userService.getUserById(id);
		clientEntity.setUserEntity(userEntity);
		clientService.addClientInformation(clientEntity);
		return "redirect:/profile/{id}";
	}
}
