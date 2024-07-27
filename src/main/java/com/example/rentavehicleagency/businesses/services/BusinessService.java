package com.example.rentavehicleagency.businesses.services;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.example.rentavehicleagency.businesses.Business;
import com.example.rentavehicleagency.businesses.entities.BusinessEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.example.rentavehicleagency.requests.entities.RequestEntity;
import com.example.rentavehicleagency.businesses.repositories.BusinessRepository;

@Slf4j
@Getter
@Service
@RequiredArgsConstructor
public class BusinessService implements Business {

	private final BusinessRepository businessRepository;
	
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
	
	public void registerProfit(BusinessEntity businessEntity, RequestEntity requestEntity) {
		long daysDifference=calculateDaysDifference(requestEntity.getStartTime(), requestEntity.getEndTime());
		float profitFromRequest= requestEntity.getVehicleEntity().getPrice()*daysDifference;
		businessEntity.setProfit(profitFromRequest+ businessEntity.getProfit());
		businessRepository.save(businessEntity);
	}
	
	public long calculateDaysDifference(LocalDateTime dateTime1, LocalDateTime dateTime2) {
		Duration duration=Duration.between(dateTime1, dateTime2);
		return duration.toDays();
	}
	
}
