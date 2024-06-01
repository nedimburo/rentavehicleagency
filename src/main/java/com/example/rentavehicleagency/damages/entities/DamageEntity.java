package com.example.rentavehicleagency.damages.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.example.rentavehicleagency.damages.Damage;
import com.example.rentavehicleagency.employees.entities.EmployeeEntity;
import com.example.rentavehicleagency.vehicles.entities.VehicleEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name="damages")
@NoArgsConstructor
@ToString(onlyExplicitlyIncluded = true, callSuper = true)
public class DamageEntity implements Damage {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="damage_type")
	private String damageType;

	@Column(name = "description")
	private String description;
	
	@Column(name="repair_cost")
	private float repairCost;
	
	@Column(name="report_date")
	private LocalDateTime reportDate;
	
	@Column(name="fix_date")
	private LocalDate fixDate;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="vehicle_id")
	private VehicleEntity vehicleEntity;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="employee_id")
	private EmployeeEntity employeeEntity;

}
