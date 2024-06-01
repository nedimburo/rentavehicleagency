package com.example.rentavehicleagency.reports.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.rentavehicleagency.reports.entities.ReportEntity;

@Repository
public interface ReportRepository extends JpaRepository<ReportEntity, Long>{
	List<ReportEntity> findByEmployeeId(Long employeeId);
	void deleteById(Long employeeId);
}
