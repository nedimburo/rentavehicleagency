package com.example.rentavehicleagency.businesses.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BusinessResponseDto {
    private String name;
    private String address;
    private String city;
    private float profit;
    private String creationDate;
}
