package com.example.rentavehicleagency.dto;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

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
	
	public VehicleDto() {
		super();
	}

	public VehicleDto(String brand, String modelName, int modelYear, String registrationPlate, int engineDisplacement,
			int horsepower, String fuel, String transmission, String color, String type, String bodyShape,
			String status, float price, LocalDate addedDate, String businessName, List<MultipartFile> images) {
		super();
		this.brand = brand;
		this.modelName = modelName;
		this.modelYear = modelYear;
		this.registrationPlate = registrationPlate;
		this.engineDisplacement = engineDisplacement;
		this.horsepower = horsepower;
		this.fuel = fuel;
		this.transmission = transmission;
		this.color = color;
		this.type = type;
		this.bodyShape = bodyShape;
		this.status = status;
		this.price = price;
		this.addedDate = addedDate;
		this.businessName = businessName;
		this.images = images;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public int getModelYear() {
		return modelYear;
	}

	public void setModelYear(int modelYear) {
		this.modelYear = modelYear;
	}

	public String getRegistrationPlate() {
		return registrationPlate;
	}

	public void setRegistrationPlate(String registrationPlate) {
		this.registrationPlate = registrationPlate;
	}

	public int getEngineDisplacement() {
		return engineDisplacement;
	}

	public void setEngineDisplacement(int engineDisplacement) {
		this.engineDisplacement = engineDisplacement;
	}

	public int getHorsepower() {
		return horsepower;
	}

	public void setHorsepower(int horsepower) {
		this.horsepower = horsepower;
	}

	public String getFuel() {
		return fuel;
	}

	public void setFuel(String fuel) {
		this.fuel = fuel;
	}

	public String getTransmission() {
		return transmission;
	}

	public void setTransmission(String transmission) {
		this.transmission = transmission;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBodyShape() {
		return bodyShape;
	}

	public void setBodyShape(String bodyShape) {
		this.bodyShape = bodyShape;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public LocalDate getAddedDate() {
		return addedDate;
	}

	public void setAddedDate(LocalDate addedDate) {
		this.addedDate = addedDate;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public List<MultipartFile> getImages() {
		return images;
	}

	public void setImages(List<MultipartFile> images) {
		this.images = images;
	}
	
}
