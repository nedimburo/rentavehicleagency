package com.example.rentavehicleagency.clients.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientRequestDto {
    private String address;
    private String contactNumber;
    private String jmbg;
    private String bankInformation;
    private String licenseNumber;
    private Long userId;
}
