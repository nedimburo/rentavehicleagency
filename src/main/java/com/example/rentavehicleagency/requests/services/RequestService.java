package com.example.rentavehicleagency.requests.services;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.example.rentavehicleagency.requests.entities.RequestEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.example.rentavehicleagency.requests.payloads.RequestDto;
import com.example.rentavehicleagency.employees.entities.EmployeeEntity;
import com.example.rentavehicleagency.requests.repositories.RequestRepository;

@Slf4j
@Getter
@Service
@RequiredArgsConstructor
public class RequestService {

	private final RequestRepository requestRepository;
	
	public void saveRequest(RequestEntity requestEntity) {
		requestEntity.setNote("None");
		requestEntity.setStatus("PENDING");
		requestEntity.setCreationDate(LocalDateTime.now());
		requestRepository.save(requestEntity);
	}
	
	public List<RequestEntity> getAllRequest(){
		return requestRepository.findAll();
	}
	
	public RequestEntity getRequestById(Long id) {
		return requestRepository.findById(id).orElse(null);
	}
	
	public void setRequestStatus(RequestEntity selectedRequestEntity, String status, EmployeeEntity employeeEntity, String note) {
		RequestEntity existingRequestEntity =requestRepository.findById(selectedRequestEntity.getId()).orElse(null);
		if (existingRequestEntity !=null) {
			existingRequestEntity.setEmployeeEntity(employeeEntity);
			existingRequestEntity.setStatus(status);
			existingRequestEntity.setNote(note);
			requestRepository.save(existingRequestEntity);
		}
	}
	
	public List<RequestEntity> getAllClientsRequestsById(Long clientId){
		return requestRepository.findByClientEntityId(clientId);
	}
	
	public List<RequestDto> getRequestsForFinances(){
		List<RequestEntity> allRequestEntities =requestRepository.findAll();
		List<RequestDto> requestsForDisplay=new ArrayList<>();
		for (int i = 0; i< allRequestEntities.size(); i++) {
			if (allRequestEntities.get(i).getStatus().equals("FINISHED")) {
				RequestDto requestDto=new RequestDto();
				requestDto.setId(allRequestEntities.get(i).getId());
				requestDto.setStartTime(allRequestEntities.get(i).getStartTime());
				requestDto.setEndTime(allRequestEntities.get(i).getEndTime());
				requestDto.setCreationDate(allRequestEntities.get(i).getCreationDate());
				requestDto.setPaymentMethod(allRequestEntities.get(i).getPaymentMethod());
				long daysDifference=calculateDaysDifference(allRequestEntities.get(i).getStartTime(), allRequestEntities.get(i).getEndTime());
				float requestProfit=daysDifference* allRequestEntities.get(i).getVehicleEntity().getPrice();
				requestDto.setRequestProfit(requestProfit);
				requestDto.setClientFullName(allRequestEntities.get(i).getClientEntity().getUserEntity().getFirstName() + " " + allRequestEntities.get(i).getClientEntity().getUserEntity().getLastName());
				requestDto.setVehicleBrandModel(allRequestEntities.get(i).getVehicleEntity().getBrand() + " " + allRequestEntities.get(i).getVehicleEntity().getModelName());
				requestsForDisplay.add(requestDto);
			}
		}
		return requestsForDisplay;
	}
	
	public long calculateDaysDifference(LocalDateTime dateTime1, LocalDateTime dateTime2) {
		Duration duration=Duration.between(dateTime1, dateTime2);
		return duration.toDays();
	}
	
	public List<RequestEntity> findRequestsByEmployeeId(Long employeeId){
		return requestRepository.findByEmployeeEntityId(employeeId);
	}
	
	public void deleteRequestById(Long id) {
		requestRepository.deleteById(id);
	}
	
	public List<RequestEntity> findRequestsByVehicleId(Long vehicleId){
		return requestRepository.findByVehicleEntityId(vehicleId);
	}
}
