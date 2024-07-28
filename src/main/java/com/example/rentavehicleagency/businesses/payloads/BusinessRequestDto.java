package com.example.rentavehicleagency.businesses.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BusinessRequestDto {
    private String name;
    private String address;
    private String city;
}
