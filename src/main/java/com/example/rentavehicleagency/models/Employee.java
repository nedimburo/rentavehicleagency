package com.example.rentavehicleagency.models;

import java.time.LocalDate;

import com.example.rentavehicleagency.businesses.entities.BusinessEntity;
import com.example.rentavehicleagency.users.entities.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name="employees")
@NoArgsConstructor
@ToString(onlyExplicitlyIncluded = true, callSuper = true)
public class Employee {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(name = "jmbg", unique = true)
	private String jmbg;

	@Column(name = "pay")
	private float pay;

	@Column(name = "address")
	private String address;

	@Column(name = "city")
	private String city;
	
	@Column(name="contact_number")
	private String contactNumber;
	
	@Column(name="employment_status")
	private String employmentStatus;
	
	@Column(name="hire_date")
	private LocalDate hireDate;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	private UserEntity userEntity;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="business_id")
	private BusinessEntity businessEntity;

}
