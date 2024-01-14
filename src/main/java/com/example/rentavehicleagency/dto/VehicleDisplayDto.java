package com.example.rentavehicleagency.dto;

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
	
	public VehicleDisplayDto() {
		super();
	}

	public VehicleDisplayDto(String brand, String modelName, int modelYear, int horsepower, String transmission,
			String fuel, String bodyType, float price, String status, String imageName) {
		super();
		this.brand = brand;
		this.modelName = modelName;
		this.modelYear = modelYear;
		this.horsepower = horsepower;
		this.transmission = transmission;
		this.fuel = fuel;
		this.bodyType = bodyType;
		this.price = price;
		this.status = status;
		this.imageName = imageName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public int getHorsepower() {
		return horsepower;
	}

	public void setHorsepower(int horsepower) {
		this.horsepower = horsepower;
	}

	public String getTransmission() {
		return transmission;
	}

	public void setTransmission(String transmission) {
		this.transmission = transmission;
	}

	public String getFuel() {
		return fuel;
	}

	public void setFuel(String fuel) {
		this.fuel = fuel;
	}

	public String getBodyType() {
		return bodyType;
	}

	public void setBodyType(String bodyType) {
		this.bodyType = bodyType;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	
}
