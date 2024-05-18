package com.example.rentavehicleagency.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name="clients")
@NoArgsConstructor
@ToString(onlyExplicitlyIncluded = true, callSuper = true)
public class Client {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(name = "is_confirmed")
	private int isConfirmed;

	@Column(name = "address")
	private String address;
	
	@Column(name="contact_number")
	private String contactNumber;

	@Column(name = "jmbg", unique = true)
	private String jmbg;
	
	@Column(name="bank_information")
	private String bankInformation;
	
	@Column(name="license_number")
	private String licenseNumber;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User user;

}
