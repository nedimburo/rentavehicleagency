package com.example.rentavehicleagency.reports.services;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.example.rentavehicleagency.reports.entities.ReportEntity;
import com.example.rentavehicleagency.reports.repositories.ReportRepository;

@Slf4j
@Getter
@Service
@RequiredArgsConstructor
public class ReportService {
	
	private final ReportRepository reportRepository;
	
	public void saveReport(ReportEntity reportEntity) {
		reportEntity.setCreationDate(LocalDateTime.now());
		reportRepository.save(reportEntity);
	}
	
	public List<ReportEntity> getAllReports(){
		return reportRepository.findAll();
	}
	
	public List<ReportEntity> findReportsByEmployeeId(Long employeeId){
		return reportRepository.findByEmployeeEntityId(employeeId);
	}
	
	public void deleteReportById(Long id) {
		reportRepository.deleteById(id);
	}

}
