package com.example.rentavehicleagency.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.rentavehicleagency.dto.DirectorDto;
import com.example.rentavehicleagency.models.Business;
import com.example.rentavehicleagency.models.Director;
import com.example.rentavehicleagency.models.User;
import com.example.rentavehicleagency.services.BusinessService;
import com.example.rentavehicleagency.services.DirectorService;
import com.example.rentavehicleagency.services.UserService;

@Controller
public class DirectorController {

	@Autowired
	private DirectorService directorService;
	
	@Autowired
	private BusinessService businessService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/add-director")
	public String addDirectorPage(Model model) {
		List<Business> allBusinesses=businessService.getAllBusinesses();
		model.addAttribute("directorDto", new DirectorDto());
		model.addAttribute("allBusinesses", allBusinesses);
		return "addDirector";
	}
	
	@PostMapping("/add-director-submit")
	public String submitDirector(@ModelAttribute("directorDto") DirectorDto directorDto) {
		User user=new User(directorDto.getFirstName(), directorDto.getLastName(), directorDto.getEmail(), 
				directorDto.getPassword(), directorDto.getNickname(), "DIRECTOR", directorDto.getGender(),
				directorDto.getBirthDate(), "default_user_image.jpg");
		userService.saveUserInstance(user);
		Business business=businessService.findBusinessByName(directorDto.getName());
		User newRegisteredUser=userService.getUserByEmail(directorDto.getEmail());
		Director newDirector=new Director();
		newDirector.setJmbg(directorDto.getJmbg());
		newDirector.setPay(directorDto.getPay());
		newDirector.setUser(newRegisteredUser);
		newDirector.setBusiness(business);
		directorService.saveDirector(newDirector);
		return "redirect:/owner-page";
	}
}
