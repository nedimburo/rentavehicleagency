package com.example.rentavehicleagency.reports.controllers;

import com.example.rentavehicleagency.reports.payloads.AllReportsResponseDto;
import com.example.rentavehicleagency.reports.payloads.ReportRequestDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.rentavehicleagency.reports.services.ReportService;

import java.util.List;

@Slf4j
@Getter
@RestController
@RequiredArgsConstructor
@RequestMapping("employee/report")
@Tags(value = @Tag(name = "Employee | Report"))
public class ReportController {

	private final ReportService service;
	
	@PostMapping("/add-new-report")
	public ResponseEntity<?> addNewReport(@RequestBody ReportRequestDto reportRequestDto) {
		return  service.addNewReport(reportRequestDto);
	}

	@GetMapping("/get-all-reports")
	public List<AllReportsResponseDto> getAllReports(){
		return service.getAllReports();
	}

	@DeleteMapping("/delete-report/{id}")
	public ResponseEntity<?> deleteReport(@PathVariable Long id){
		return service.deleteReportById(id);
	}
}
