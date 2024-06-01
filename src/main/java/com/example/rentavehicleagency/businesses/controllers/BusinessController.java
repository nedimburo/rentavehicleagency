package com.example.rentavehicleagency.businesses.controllers;

import com.example.rentavehicleagency.businesses.entities.BusinessEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.rentavehicleagency.businesses.services.BusinessService;

@Controller
public class BusinessController {

	@Autowired
	private BusinessService businessService;
	
	@GetMapping("/add-business")
	public String businessPage(Model model) {
		model.addAttribute("business", new BusinessEntity());
		return "addBusiness";
	}
	
	@PostMapping("/add-business-submit")
	public String businessSubmitForm(@ModelAttribute("business") BusinessEntity businessEntity) {
		businessService.registerNewBusiness(businessEntity);
		return "redirect:/owner-page";
	}
}
