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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name="employees", uniqueConstraints = {
		@UniqueConstraint(columnNames="jmbg")
})
public class Employee {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String jmbg;
	
	private float pay;
	
	private String address;
	
	private String city;
	
	@Column(name="contact_number")
	private String contactNumber;
	
	@Column(name="employment_status")
	private String employmentStatus;
	
	@Column(name="hire_date")
	private LocalDate hireDate;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User user;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="business_id")
	private Business business;

	public Employee() {
		super();
	}

	public Employee(String jmbg, float pay, String address, String city, String contactNumber, String employmentStatus,
			LocalDate hireDate, User user, Business business) {
		super();
		this.jmbg = jmbg;
		this.pay = pay;
		this.address = address;
		this.city = city;
		this.contactNumber = contactNumber;
		this.employmentStatus = employmentStatus;
		this.hireDate = hireDate;
		this.user = user;
		this.business = business;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getJmbg() {
		return jmbg;
	}

	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
	}

	public float getPay() {
		return pay;
	}

	public void setPay(float pay) {
		this.pay = pay;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getEmploymentStatus() {
		return employmentStatus;
	}

	public void setEmploymentStatus(String employmentStatus) {
		this.employmentStatus = employmentStatus;
	}

	public LocalDate getHireDate() {
		return hireDate;
	}

	public void setHireDate(LocalDate hireDate) {
		this.hireDate = hireDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Business getBusiness() {
		return business;
	}

	public void setBusiness(Business business) {
		this.business = business;
	}
	
}
