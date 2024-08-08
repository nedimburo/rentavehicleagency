package com.example.rentavehicleagency.vehicles.entities;

import java.time.LocalDate;

import com.example.rentavehicleagency.businesses.entities.BusinessEntity;
import com.example.rentavehicleagency.vehicles.Vehicle;
import jakarta.persistence.*;
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

	@Column(name = "brand")
	private String brand;
	
	@Column(name = "model_name")
	private String modelName;
	
	@Column(name = "model_year")
	private int modelYear;

	@Column(name = "registration_plate", unique = true)
	private String registrationPlate;
	
	@Column(name = "engine_displacement")
	private int engineDisplacement;

	@Column(name = "horsepower")
	private int horsepower;

	@Column(name = "fuel")
	@Enumerated(EnumType.STRING)
	private FuelType fuel;

	@Column(name = "transmission")
	@Enumerated(EnumType.STRING)
	private TransmissionType transmission;

	@Column(name = "color")
	private String color;

	@Column(name = "type")
	@Enumerated(EnumType.STRING)
	private VehicleType type;
	
	@Column(name = "body_shape")
	@Enumerated(EnumType.STRING)
	private BodyShape bodyShape;

	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private VehicleStatus status;

	@Column(name = "price")
	private float price;
	
	@Column(name = "added_date")
	private LocalDate addedDate;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name= " business_id")
	private BusinessEntity businessEntity;

}
