package com.example.rentavehicleagency.vehicles.payloads;

import java.util.List;

import com.example.rentavehicleagency.vehicles.entities.BodyShape;
import com.example.rentavehicleagency.vehicles.entities.FuelType;
import com.example.rentavehicleagency.vehicles.entities.TransmissionType;
import com.example.rentavehicleagency.vehicles.entities.VehicleType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VehicleRequestDto {
	private String brand;
	private String modelName;
	private int modelYear;
	private String registrationPlate;
	private int engineDisplacement;
	private int horsepower;
	private FuelType fuel;
	private TransmissionType transmission;
	private String color;	
	private VehicleType type;
	private BodyShape bodyShape;
	private float price;
	private String businessName;
	private List<MultipartFile> images;
}
