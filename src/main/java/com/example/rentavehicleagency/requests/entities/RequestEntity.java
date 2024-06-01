package com.example.rentavehicleagency.requests.entities;

import java.time.LocalDateTime;

import com.example.rentavehicleagency.clients.entities.ClientEntity;
import com.example.rentavehicleagency.employees.entities.EmployeeEntity;
import com.example.rentavehicleagency.requests.Request;
import com.example.rentavehicleagency.vehicles.entities.VehicleEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name="requests")
@NoArgsConstructor
@ToString(onlyExplicitlyIncluded = true, callSuper = true)
public class RequestEntity implements Request {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "status")
	private String status;
	
	@Column(name="start_time")
	private LocalDateTime startTime;
	
	@Column(name="end_time")
	private LocalDateTime endTime;
	
	@Column(name="creation_date")
	private LocalDateTime creationDate;
	
	@Column(name="payment_method")
	private String paymentMethod;
	
	@Lob
	@Column(name = "note")
	private String note;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="client_id")
	private ClientEntity clientEntity;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="vehicle_id")
	private VehicleEntity vehicleEntity;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="employee_id")
	private EmployeeEntity employeeEntity;

}
