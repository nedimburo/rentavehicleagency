package com.example.rentavehicleagency.directors.payloads;

import com.example.rentavehicleagency.users.entities.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DirectorRequestDto {
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String nickname;
	private Gender gender;
	private String birthDate;
	private String jmbg;
	private float pay;
	private String businessName;
}
