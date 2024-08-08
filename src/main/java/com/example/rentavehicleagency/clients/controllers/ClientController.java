package com.example.rentavehicleagency.clients.controllers;

import com.example.rentavehicleagency.clients.payloads.ClientRequestDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.rentavehicleagency.clients.services.ClientService;

@Slf4j
@Getter
@RestController
@RequiredArgsConstructor
@RequestMapping("user/client")
@Tags(value = @Tag(name = "User | Client"))
public class ClientController {
	
	private final ClientService service;
	
	@PostMapping("/submit-client-information")
	public ResponseEntity<?> submitClientInformation(@RequestBody ClientRequestDto clientRequestDto) {
		return service.addClientInformation(clientRequestDto);
	}
}
