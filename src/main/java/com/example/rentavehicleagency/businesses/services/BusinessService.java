package com.example.rentavehicleagency.businesses.services;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.example.rentavehicleagency.businesses.Business;
import com.example.rentavehicleagency.businesses.entities.BusinessEntity;
import com.example.rentavehicleagency.businesses.payloads.BusinessRequestDto;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.rentavehicleagency.requests.entities.RequestEntity;
import com.example.rentavehicleagency.businesses.repositories.BusinessRepository;

@Slf4j
@Getter
@Service
@RequiredArgsConstructor
public class BusinessService implements Business {

	private final BusinessRepository repository;

	@Transactional
	public ResponseEntity<?> addNewBusiness(BusinessRequestDto businessRequestDto) {
		BusinessEntity businessEntity = new BusinessEntity();
		businessEntity.setName(businessRequestDto.getName());
		businessEntity.setAddress(businessRequestDto.getAddress());
		businessEntity.setCity(businessRequestDto.getCity());
		businessEntity.setCreationDate(LocalDate.now());
		businessEntity.setProfit(0);
		repository.save(businessEntity);
		return new ResponseEntity<>("Business has been created successfully.", HttpStatus.OK);
	}
	
	public List<BusinessEntity> getAllBusinesses(){
		return repository.findAll();
	}
	
	public BusinessEntity findBusinessByName(String name) {
		return repository.findByName(name);
	}
	
	public BusinessEntity getBusinessById(Long id) {
		return repository.findById(id).orElse(null);
	}
	
	public void registerProfit(BusinessEntity businessEntity, RequestEntity requestEntity) {
		long daysDifference=calculateDaysDifference(requestEntity.getStartTime(), requestEntity.getEndTime());
		float profitFromRequest= requestEntity.getVehicleEntity().getPrice()*daysDifference;
		businessEntity.setProfit(profitFromRequest+ businessEntity.getProfit());
		repository.save(businessEntity);
	}
	
	public long calculateDaysDifference(LocalDateTime dateTime1, LocalDateTime dateTime2) {
		Duration duration=Duration.between(dateTime1, dateTime2);
		return duration.toDays();
	}
	
}
