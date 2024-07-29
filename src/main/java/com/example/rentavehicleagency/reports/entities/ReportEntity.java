package com.example.rentavehicleagency.reports.entities;

import java.time.LocalDateTime;

import com.example.rentavehicleagency.employees.entities.EmployeeEntity;
import com.example.rentavehicleagency.reports.Report;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name="reports")
@NoArgsConstructor
@ToString(onlyExplicitlyIncluded = true, callSuper = true)
public class ReportEntity implements Report {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(name = "title")
	private String title;
	
	@Lob
	@Column(name = "content")
	private String content;

	@Column(name = "priority")
	@Enumerated(EnumType.STRING)
	private PriorityType priority = PriorityType.LOW;
	
	@Column(name="creation_date")
	private LocalDateTime creationDate;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="employee_id")
	private EmployeeEntity employeeEntity;

}
