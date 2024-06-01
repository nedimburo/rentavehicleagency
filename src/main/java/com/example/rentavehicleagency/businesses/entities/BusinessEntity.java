package com.example.rentavehicleagency.businesses.entities;

import java.time.LocalDate;

import com.example.rentavehicleagency.businesses.Business;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name="businesses")
@NoArgsConstructor
@ToString(onlyExplicitlyIncluded = true, callSuper = true)
public class BusinessEntity implements Business {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", unique = true)
	private String name;

	@Column(name = "address")
	private String address;

	@Column(name = "city")
	private String city;

	@Column(name = "profit")
	private float profit;
	
	@Column(name="creation_date")
	private LocalDate creationDate;
	
}
