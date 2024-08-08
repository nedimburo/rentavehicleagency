package com.example.rentavehicleagency.businesses.controllers;

import com.example.rentavehicleagency.businesses.payloads.BusinessResponseDto;
import com.example.rentavehicleagency.businesses.payloads.BusinessRequestDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.rentavehicleagency.businesses.services.BusinessService;

import java.util.List;

@Slf4j
@Getter
@RestController
@RequiredArgsConstructor
@RequestMapping("owner/business")
@Tags(value = @Tag(name = "Owner | Business"))
public class BusinessController {

	private final BusinessService service;
	
	@PostMapping("/add-new-business")
	public ResponseEntity<?> addNewBusiness(@RequestBody BusinessRequestDto businessRequestDto) {
		return service.addNewBusiness(businessRequestDto);
	}

	@GetMapping("/get-all-businesses")
	public List<BusinessResponseDto> getAllBusinesses(){
		return service.getAllBusinesses();
	}
}
