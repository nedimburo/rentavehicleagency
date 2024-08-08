package com.example.rentavehicleagency.directors.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.rentavehicleagency.directors.payloads.DirectorRequestDto;
import com.example.rentavehicleagency.directors.services.DirectorService;

@Slf4j
@Getter
@RestController
@RequiredArgsConstructor
@RequestMapping("owner/director")
@Tags(value = @Tag(name = "Owner | Director"))
public class DirectorController {

	private final DirectorService service;
	
	@PostMapping("/add-director-submit")
	public ResponseEntity<?> addDirector(@RequestBody DirectorRequestDto directorRequestDto) {
		return service.addDirector(directorRequestDto);
	}
}
