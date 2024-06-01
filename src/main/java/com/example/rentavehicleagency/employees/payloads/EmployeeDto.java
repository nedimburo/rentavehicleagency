package com.example.rentavehicleagency.employees.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {
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
	private String address;
	private String city;
	private String contactNumber;
	private String employmentStatus;
	private LocalDate hireDate;
	private String name;

}
