package com.example.rentavehicleagency.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="vehicle_images")
public class ImageVehicle {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Lob
	@Column(name="image_name")
	private String imageName;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="vehicle_id")
	private Vehicle vehicle;

	public ImageVehicle() {
		super();
	}

	public ImageVehicle(Long id, String imageName, Vehicle vehicle) {
		super();
		this.id = id;
		this.imageName = imageName;
		this.vehicle = vehicle;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
	
}
