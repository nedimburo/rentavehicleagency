package com.example.rentavehicleagency.vehicleImages.entities;

import com.example.rentavehicleagency.vehicles.entities.VehicleEntity;
import com.example.rentavehicleagency.vehicleImages.VehicleImage;
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
import lombok.*;

@Getter
@Setter
@Entity
@Table(name="vehicle_images")
@NoArgsConstructor
@ToString(onlyExplicitlyIncluded = true, callSuper = true)
public class VehicleImageEntity implements VehicleImage {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Lob
	@Column(name="image_name")
	private String imageName;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="vehicle_id")
	private VehicleEntity vehicleEntity;

}
