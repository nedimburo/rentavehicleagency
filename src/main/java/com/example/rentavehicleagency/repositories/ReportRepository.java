package com.example.rentavehicleagency.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.rentavehicleagency.models.Report;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long>{
	List<Report> findByEmployeeId(Long employeeId);
	void deleteById(Long employeeId);
}
