package com.example.rentavehicleagency.dto;

import java.time.LocalDateTime;

public class RequestDto {
	private Long id;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private LocalDateTime creationDate;
	private String paymentMethod;
	private float requestProfit;
	private String clientFullName;
	private String vehicleBrandModel;
	
	public RequestDto() {
		super();
	}

	public RequestDto(Long id, LocalDateTime startTime, LocalDateTime endTime, LocalDateTime creationDate,
			String paymentMethod, float requestProfit, String clientFullName, String vehicleBrandModel) {
		super();
		this.id = id;
		this.startTime = startTime;
		this.endTime = endTime;
		this.creationDate = creationDate;
		this.paymentMethod = paymentMethod;
		this.requestProfit = requestProfit;
		this.clientFullName = clientFullName;
		this.vehicleBrandModel = vehicleBrandModel;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public float getRequestProfit() {
		return requestProfit;
	}

	public void setRequestProfit(float requestProfit) {
		this.requestProfit = requestProfit;
	}

	public String getClientFullName() {
		return clientFullName;
	}

	public void setClientFullName(String clientFullName) {
		this.clientFullName = clientFullName;
	}

	public String getVehicleBrandModel() {
		return vehicleBrandModel;
	}

	public void setVehicleBrandModel(String vehicleBrandModel) {
		this.vehicleBrandModel = vehicleBrandModel;
	}
	
}
