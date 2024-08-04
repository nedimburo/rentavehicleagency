package com.example.rentavehicleagency.employees.payloads;

import com.example.rentavehicleagency.employees.entities.EmploymentStatus;
import com.example.rentavehicleagency.users.entities.Gender;
import com.example.rentavehicleagency.users.entities.RoleType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRequestDto {
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String nickname;
	private RoleType role;
	private Gender gender;
	private String birthDate;
	private String jmbg;
	private float pay;
	private String address;
	private String city;
	private String contactNumber;
	private EmploymentStatus employmentStatus;
	private String businessName;

}
