package com.example.rentavehicleagency.requests.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestDto {
	private Long id;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private LocalDateTime creationDate;
	private String paymentMethod;
	private float requestProfit;
	private String clientFullName;
	private String vehicleBrandModel;
}
