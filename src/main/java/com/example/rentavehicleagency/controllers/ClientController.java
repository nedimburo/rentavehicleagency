package com.example.rentavehicleagency.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.rentavehicleagency.models.Client;
import com.example.rentavehicleagency.models.User;
import com.example.rentavehicleagency.services.ClientService;
import com.example.rentavehicleagency.services.UserService;

@Controller
public class ClientController {
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private UserService userService;

	@GetMapping("/profile/{id}/add-information")
	public String addInformationPage(@PathVariable Long id, Model model) {
		Client client=clientService.findClientByUserId(id);
		model.addAttribute("client", client);
		return "addInformation";
	}
	
	@PostMapping("/profile/{id}/submit-information")
	public String submitInformation(@ModelAttribute("client") Client client, @PathVariable Long id) {
		User user=userService.getUserById(id);
		client.setUser(user);
		clientService.addClientInformation(client);
		return "redirect:/profile/{id}";
	}
}
