package com.example.rentavehicleagency.services;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.rentavehicleagency.models.Business;
import com.example.rentavehicleagency.models.Request;
import com.example.rentavehicleagency.repositories.BusinessRepository;

@Service
public class BusinessService {

	@Autowired
	private BusinessRepository businessRepository;
	
	public void registerNewBusiness(Business business) {
		business.setCreationDate(LocalDate.now());
		business.setProfit(0);
		businessRepository.save(business);
	}
	
	public List<Business> getAllBusinesses(){
		return businessRepository.findAll();
	}
	
	public Business findBusinessByName(String name) {
		return businessRepository.findByName(name);
	}
	
	public Business getBusinessById(Long id) {
		return businessRepository.findById(id).orElse(null);
	}
	
	public void registerProfit(Business business, Request request) {
		long daysDifference=calculateDaysDifference(request.getStartTime(), request.getEndTime());
		float profitFromRequest=request.getVehicle().getPrice()*daysDifference;
		business.setProfit(profitFromRequest+business.getProfit());
		businessRepository.save(business);
	}
	
	public long calculateDaysDifference(LocalDateTime dateTime1, LocalDateTime dateTime2) {
		Duration duration=Duration.between(dateTime1, dateTime2);
		return duration.toDays();
	}
	
}
