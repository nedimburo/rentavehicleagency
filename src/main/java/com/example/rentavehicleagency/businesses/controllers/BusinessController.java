package com.example.rentavehicleagency.businesses.controllers;

import com.example.rentavehicleagency.businesses.payloads.BusinessRequestDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.rentavehicleagency.businesses.services.BusinessService;

@Slf4j
@Getter
@RestController
@RequiredArgsConstructor
@RequestMapping("public/business")
@Tags(value = @Tag(name = "Public | Business"))
public class BusinessController {

	private final BusinessService businessService;
	
	@PostMapping("/add-new-busines")
	public ResponseEntity<?> addNewBusiness(@RequestBody BusinessRequestDto businessRequestDto) {
		return businessService.addNewBusiness(businessRequestDto);
	}
}
