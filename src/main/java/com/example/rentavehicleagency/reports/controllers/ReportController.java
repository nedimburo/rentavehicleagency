package com.example.rentavehicleagency.reports.controllers;

import com.example.rentavehicleagency.reports.payloads.ReportRequestDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.rentavehicleagency.reports.services.ReportService;

@Slf4j
@Getter
@RestController
@RequiredArgsConstructor
@RequestMapping("public/report")
@Tags(value = @Tag(name = "Public | Report"))
public class ReportController {

	private final ReportService reportService;
	
	@PostMapping("/add-new-report")
	private ResponseEntity<?> addNewReport(@RequestBody ReportRequestDto reportRequestDto) {
		return  reportService.addNewReport(reportRequestDto);
	}
}
