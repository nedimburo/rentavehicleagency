package com.example.rentavehicleagency.models;

import java.time.LocalDateTime;

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

@Entity
@Table(name="requests")
public class Request {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
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
	private String note;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="client_id")
	private Client client;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="vehicle_id")
	private Vehicle vehicle;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="employee_id")
	private Employee employee;

	public Request() {
		super();
	}

	public Request(String status, LocalDateTime startTime, LocalDateTime endTime, LocalDateTime creationDate,
			String paymentMethod, String note, Client client, Vehicle vehicle, Employee employee) {
		super();
		this.status = status;
		this.startTime = startTime;
		this.endTime = endTime;
		this.creationDate = creationDate;
		this.paymentMethod = paymentMethod;
		this.note = note;
		this.client = client;
		this.vehicle = vehicle;
		this.employee = employee;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}
