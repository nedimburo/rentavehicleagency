package com.example.rentavehicleagency.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
	
}
