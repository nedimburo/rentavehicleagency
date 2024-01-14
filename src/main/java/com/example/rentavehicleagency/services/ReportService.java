package com.example.rentavehicleagency.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.rentavehicleagency.models.Report;
import com.example.rentavehicleagency.repositories.ReportRepository;

@Service
public class ReportService {
	
	@Autowired
	private ReportRepository reportRepository;
	
	public void saveReport(Report report) {
		report.setCreationDate(LocalDateTime.now());
		reportRepository.save(report);
	}
	
	public List<Report> getAllReports(){
		return reportRepository.findAll();
	}
	
	public List<Report> findReportsByEmployeeId(Long employeeId){
		return reportRepository.findByEmployeeId(employeeId);
	}
	
	public void deleteReportById(Long id) {
		reportRepository.deleteById(id);
	}

}
