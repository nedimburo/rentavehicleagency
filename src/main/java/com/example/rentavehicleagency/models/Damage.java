package com.example.rentavehicleagency.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="damages")
public class Damage {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="damage_type")
	private String damageType;
	
	private String description;
	
	@Column(name="repair_cost")
	private float repairCost;
	
	@Column(name="report_date")
	private LocalDateTime reportDate;
	
	@Column(name="fix_date")
	private LocalDate fixDate;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="vehicle_id")
	private Vehicle vehicle;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="employee_id")
	private Employee employee;

	public Damage() {
		super();
	}

	public Damage(String damageType, String description, float repairCost, LocalDateTime reportDate, LocalDate fixDate,
			Vehicle vehicle, Employee employee) {
		super();
		this.damageType = damageType;
		this.description = description;
		this.repairCost = repairCost;
		this.reportDate = reportDate;
		this.fixDate = fixDate;
		this.vehicle = vehicle;
		this.employee = employee;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDamageType() {
		return damageType;
	}

	public void setDamageType(String damageType) {
		this.damageType = damageType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getRepairCost() {
		return repairCost;
	}

	public void setRepairCost(float repairCost) {
		this.repairCost = repairCost;
	}

	public LocalDateTime getReportDate() {
		return reportDate;
	}

	public void setReportDate(LocalDateTime reportDate) {
		this.reportDate = reportDate;
	}

	public LocalDate getFixDate() {
		return fixDate;
	}

	public void setFixDate(LocalDate fixDate) {
		this.fixDate = fixDate;
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
