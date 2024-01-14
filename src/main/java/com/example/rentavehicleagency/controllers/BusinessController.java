package com.example.rentavehicleagency.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.rentavehicleagency.models.Business;
import com.example.rentavehicleagency.services.BusinessService;

@Controller
public class BusinessController {

	@Autowired
	private BusinessService businessService;
	
	@GetMapping("/add-business")
	public String businessPage(Model model) {
		model.addAttribute("business", new Business());
		return "addBusiness";
	}
	
	@PostMapping("/add-business-submit")
	public String businessSubmitForm(@ModelAttribute("business") Business business) {
		businessService.registerNewBusiness(business);
		return "redirect:/owner-page";
	}
}
