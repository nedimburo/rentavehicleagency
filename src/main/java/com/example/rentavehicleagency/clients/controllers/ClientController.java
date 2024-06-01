package com.example.rentavehicleagency.clients.controllers;

import com.example.rentavehicleagency.clients.entities.ClientEntity;
import com.example.rentavehicleagency.users.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.rentavehicleagency.clients.services.ClientService;
import com.example.rentavehicleagency.users.services.UserService;

@Controller
public class ClientController {
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private UserService userService;

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
