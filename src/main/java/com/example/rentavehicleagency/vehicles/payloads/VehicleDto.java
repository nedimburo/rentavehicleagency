package com.example.rentavehicleagency.vehicles.payloads;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VehicleDto {
	private String brand;
	private String modelName;
	private int modelYear;
	private String registrationPlate;
	private int engineDisplacement;
	private int horsepower;
	private String fuel;	
	private String transmission;	
	private String color;	
	private String type;
	private String bodyShape;
	private String status;
	private float price;
	private LocalDate addedDate;
	private String businessName;
	private List<MultipartFile> images;
}
