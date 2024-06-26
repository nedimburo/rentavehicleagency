package com.example.rentavehicleagency.vehicles.entities;

import java.time.LocalDate;

import com.example.rentavehicleagency.businesses.entities.BusinessEntity;
import com.example.rentavehicleagency.vehicles.Vehicle;
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
@Table(name="vehicles")
@NoArgsConstructor
@ToString(onlyExplicitlyIncluded = true, callSuper = true)
public class VehicleEntity implements Vehicle {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String brand;
	
	@Column(name="model_name")
	private String modelName;
	
	@Column(name="model_year")
	private int modelYear;

	@Column(name = "registration_plate", unique = true)
	private String registrationPlate;
	
	@Column(name="engine_displacement")
	private int engineDisplacement;
	
	private int horsepower;
	
	private String fuel;
	
	private String transmission;
	
	private String color;
	
	private String type;
	
	@Column(name="body_shape")
	private String bodyShape;
	
	private String status;
	
	private float price;
	
	@Column(name="added_date")
	private LocalDate addedDate;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="business_id")
	private BusinessEntity businessEntity;

}
