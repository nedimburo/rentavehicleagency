package com.example.rentavehicleagency.reports.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.rentavehicleagency.reports.entities.ReportEntity;
import com.example.rentavehicleagency.reports.repositories.ReportRepository;

@Service
public class ReportService {
	
	@Autowired
	private ReportRepository reportRepository;
	
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
