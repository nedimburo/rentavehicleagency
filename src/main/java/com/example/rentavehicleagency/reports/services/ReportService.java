package com.example.rentavehicleagency.reports.services;

import java.time.LocalDateTime;
import java.util.List;

import com.example.rentavehicleagency.employees.entities.EmployeeEntity;
import com.example.rentavehicleagency.employees.services.EmployeeService;
import com.example.rentavehicleagency.reports.payloads.ReportRequestDto;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.rentavehicleagency.reports.entities.ReportEntity;
import com.example.rentavehicleagency.reports.repositories.ReportRepository;

@Slf4j
@Getter
@Service
@RequiredArgsConstructor
public class ReportService {
	
	private final ReportRepository repository;

	private final EmployeeService employeeService;

	@Transactional
	public ResponseEntity<?> addNewReport(ReportRequestDto reportRequestDto) {
		ReportEntity reportEntity = new ReportEntity();
		reportEntity.setTitle(reportRequestDto.getTitle());
		reportEntity.setContent(reportRequestDto.getContent());
		reportEntity.setPriority(reportRequestDto.getPriority());
		reportEntity.setCreationDate(LocalDateTime.now());
		EmployeeEntity employeeEntity = employeeService.findEmployeeByUserId(reportRequestDto.getUserId());
		reportEntity.setEmployeeEntity(employeeEntity);
		repository.save(reportEntity);
		return new ResponseEntity<>("Report has been created successfully.", HttpStatus.OK);
	}
	
	public List<ReportEntity> getAllReports(){
		return repository.findAll();
	}
	
	public List<ReportEntity> findReportsByEmployeeId(Long employeeId){
		return repository.findByEmployeeEntityId(employeeId);
	}
	
	public void deleteReportById(Long id) {
		repository.deleteById(id);
	}

}
