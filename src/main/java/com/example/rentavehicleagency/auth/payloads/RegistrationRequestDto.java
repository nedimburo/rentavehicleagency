package com.example.rentavehicleagency.auth.payloads;

import com.example.rentavehicleagency.users.entities.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationRequestDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String nickname;
    private Gender gender;
    private String birthDate;
}
