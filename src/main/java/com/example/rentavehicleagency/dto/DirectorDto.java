package com.example.rentavehicleagency.dto;

import java.time.LocalDate;

public class DirectorDto {
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String nickname;
	private String role;
	private String gender;
	private LocalDate birthDate;
	private String profileImage;
	private String jmbg;
	private float pay;
	private LocalDate hireDate;
	private String name;
	
	public DirectorDto() {
		super();
	}

	public DirectorDto(String firstName, String lastName, String email, String password, String nickname, String role,
			String gender, LocalDate birthDate, String profileImage, String jmbg, float pay, LocalDate hireDate,
			String name) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.nickname = nickname;
		this.role = role;
		this.gender = gender;
		this.birthDate = birthDate;
		this.profileImage = profileImage;
		this.jmbg = jmbg;
		this.pay = pay;
		this.hireDate = hireDate;
		this.name = name;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public String getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
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

	public LocalDate getHireDate() {
		return hireDate;
	}

	public void setHireDate(LocalDate hireDate) {
		this.hireDate = hireDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
