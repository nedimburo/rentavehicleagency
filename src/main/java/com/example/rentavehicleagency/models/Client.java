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
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name="clients", uniqueConstraints= {
		@UniqueConstraint(columnNames="jmbg")
})
public class Client {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private int isConfirmed;
	
	private String address;
	
	@Column(name="contact_number")
	private String contactNumber;
	
	private String jmbg;
	
	@Column(name="bank_information")
	private String bankInformation;
	
	@Column(name="license_number")
	private String licenseNumber;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User user;

	public Client() {
		super();
	}

	public Client(int isConfirmed, String address, String contactNumber, String jmbg, String bankInformation,
			String licenseNumber, User user) {
		super();
		this.isConfirmed = isConfirmed;
		this.address = address;
		this.contactNumber = contactNumber;
		this.jmbg = jmbg;
		this.bankInformation = bankInformation;
		this.licenseNumber = licenseNumber;
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getIsConfirmed() {
		return isConfirmed;
	}

	public void setIsConfirmed(int isConfirmed) {
		this.isConfirmed = isConfirmed;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getJmbg() {
		return jmbg;
	}

	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
	}

	public String getBankInformation() {
		return bankInformation;
	}

	public void setBankInformation(String bankInformation) {
		this.bankInformation = bankInformation;
	}

	public String getLicenseNumber() {
		return licenseNumber;
	}

	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
