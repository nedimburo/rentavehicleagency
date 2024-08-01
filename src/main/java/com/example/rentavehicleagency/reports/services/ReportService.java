package com.example.rentavehicleagency.reports.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.example.rentavehicleagency.employees.entities.EmployeeEntity;
import com.example.rentavehicleagency.employees.services.EmployeeService;
import com.example.rentavehicleagency.reports.payloads.AllReportsResponseDto;
import com.example.rentavehicleagency.reports.payloads.ReportEmployeeDto;
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

	@Transactional
	public List<AllReportsResponseDto> getAllReports(){
		List<ReportEntity> allReports = repository.findAll();
		List<AllReportsResponseDto> responseReports = new ArrayList<>();
		for (ReportEntity report : allReports){
			AllReportsResponseDto formattedReport = new AllReportsResponseDto();
			formattedReport.setId(report.getId());
			formattedReport.setTitle(report.getTitle());
			formattedReport.setContent(report.getContent());
			formattedReport.setPriority(report.getPriority().toString());
			formattedReport.setCreationDate(report.getCreationDate().toString());
			EmployeeEntity employee = employeeService.findById(report.getEmployeeEntity().getId());
			ReportEmployeeDto formattedEmployee = new ReportEmployeeDto();
			formattedEmployee.setId(employee.getUserEntity().getId());
			formattedEmployee.setFirstName(employee.getUserEntity().getFirstName());
			formattedEmployee.setLastName(employee.getUserEntity().getLastName());
			formattedEmployee.setNickname(employee.getUserEntity().getNickname());
			formattedEmployee.setEmail(employee.getUserEntity().getEmail());
			formattedEmployee.setRole(String.valueOf(employee.getUserEntity().getRole()));
			formattedReport.setEmployee(formattedEmployee);
			responseReports.add(formattedReport);
		}
		return responseReports;
	}
	
	public List<ReportEntity> findReportsByEmployeeId(Long employeeId){
		return repository.findByEmployeeEntityId(employeeId);
	}

	@Transactional
	public ResponseEntity<?> deleteReportById(Long id) {
		repository.deleteById(id);
		return new ResponseEntity<>("Report has been deleted successfully.", HttpStatus.OK);
	}

}
