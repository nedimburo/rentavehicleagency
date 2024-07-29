package com.example.rentavehicleagency.reports.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReportEmployeeDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String nickname;
    private String email;
    private String role;
}
