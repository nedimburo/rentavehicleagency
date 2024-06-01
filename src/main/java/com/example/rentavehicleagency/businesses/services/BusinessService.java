package com.example.rentavehicleagency.businesses.services;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.example.rentavehicleagency.businesses.entities.BusinessEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.rentavehicleagency.models.Request;
import com.example.rentavehicleagency.businesses.repositories.BusinessRepository;

@Service
public class BusinessService {

	@Autowired
	private BusinessRepository businessRepository;
	
	public void registerNewBusiness(BusinessEntity businessEntity) {
		businessEntity.setCreationDate(LocalDate.now());
		businessEntity.setProfit(0);
		businessRepository.save(businessEntity);
	}
	
	public List<BusinessEntity> getAllBusinesses(){
		return businessRepository.findAll();
	}
	
	public BusinessEntity findBusinessByName(String name) {
		return businessRepository.findByName(name);
	}
	
	public BusinessEntity getBusinessById(Long id) {
		return businessRepository.findById(id).orElse(null);
	}
	
	public void registerProfit(BusinessEntity businessEntity, Request request) {
		long daysDifference=calculateDaysDifference(request.getStartTime(), request.getEndTime());
		float profitFromRequest=request.getVehicle().getPrice()*daysDifference;
		businessEntity.setProfit(profitFromRequest+ businessEntity.getProfit());
		businessRepository.save(businessEntity);
	}
	
	public long calculateDaysDifference(LocalDateTime dateTime1, LocalDateTime dateTime2) {
		Duration duration=Duration.between(dateTime1, dateTime2);
		return duration.toDays();
	}
	
}
