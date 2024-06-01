package com.example.rentavehicleagency.vehicles.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VehicleDisplayDto {
	private Long id;
	private String brand;
	private String modelName;
	private int modelYear;
	private int horsepower;
	private String transmission;
	private String fuel;
	private String bodyType;
	private float price;
	private String status;
	private String imageName;
}
