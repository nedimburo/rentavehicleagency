package com.example.rentavehicleagency.models;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name="vehicles", uniqueConstraints = {
		@UniqueConstraint(columnNames="registrationPlate")
})
public class Vehicle {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String brand;
	
	@Column(name="model_name")
	private String modelName;
	
	@Column(name="model_year")
	private int modelYear;
	
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
	private Business business;

	public Vehicle() {
		super();
	}

	public Vehicle(String brand, String modelName, int modelYear, String registrationPlate, int engineDisplacement,
			int horsepower, String fuel, String transmission, String color, String type, String bodyShape,
			String status, float price, LocalDate addedDate, Business business) {
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
		this.business = business;
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

	public Business getBusiness() {
		return business;
	}

	public void setBusiness(Business business) {
		this.business = business;
	}
	
}
