package com.example.rentavehicleagency.services;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.rentavehicleagency.dto.RequestDto;
import com.example.rentavehicleagency.models.Employee;
import com.example.rentavehicleagency.models.Request;
import com.example.rentavehicleagency.repositories.RequestRepository;

@Service
public class RequestService {

	@Autowired
	private RequestRepository requestRepository;
	
	public void saveRequest(Request request) {
		request.setNote("None");
		request.setStatus("PENDING");
		request.setCreationDate(LocalDateTime.now());
		requestRepository.save(request);
	}
	
	public List<Request> getAllRequest(){
		return requestRepository.findAll();
	}
	
	public Request getRequestById(Long id) {
		return requestRepository.findById(id).orElse(null);
	}
	
	public void setRequestStatus(Request selectedRequest, String status, Employee employee, String note) {
		Request existingRequest=requestRepository.findById(selectedRequest.getId()).orElse(null);
		if (existingRequest!=null) {
			existingRequest.setEmployee(employee);
			existingRequest.setStatus(status);
			existingRequest.setNote(note);
			requestRepository.save(existingRequest);
		}
	}
	
	public List<Request> getAllClientsRequestsById(Long clientId){
		return requestRepository.findByClientId(clientId);
	}
	
	public List<RequestDto> getRequestsForFinances(){
		List<Request> allRequests=requestRepository.findAll();
		List<RequestDto> requestsForDisplay=new ArrayList<>();
		for (int i=0; i<allRequests.size(); i++) {
			if (allRequests.get(i).getStatus().equals("FINISHED")) {
				RequestDto requestDto=new RequestDto();
				requestDto.setId(allRequests.get(i).getId());
				requestDto.setStartTime(allRequests.get(i).getStartTime());
				requestDto.setEndTime(allRequests.get(i).getEndTime());
				requestDto.setCreationDate(allRequests.get(i).getCreationDate());
				requestDto.setPaymentMethod(allRequests.get(i).getPaymentMethod());
				long daysDifference=calculateDaysDifference(allRequests.get(i).getStartTime(), allRequests.get(i).getEndTime());
				float requestProfit=daysDifference*allRequests.get(i).getVehicle().getPrice();
				requestDto.setRequestProfit(requestProfit);
				requestDto.setClientFullName(allRequests.get(i).getClientEntity().getUserEntity().getFirstName() + " " + allRequests.get(i).getClientEntity().getUserEntity().getLastName());
				requestDto.setVehicleBrandModel(allRequests.get(i).getVehicle().getBrand() + " " + allRequests.get(i).getVehicle().getModelName());
				requestsForDisplay.add(requestDto);
			}
		}
		return requestsForDisplay;
	}
	
	public long calculateDaysDifference(LocalDateTime dateTime1, LocalDateTime dateTime2) {
		Duration duration=Duration.between(dateTime1, dateTime2);
		return duration.toDays();
	}
	
	public List<Request> findRequestsByEmployeeId(Long employeeId){
		return requestRepository.findByEmployeeId(employeeId);
	}
	
	public void deleteRequestById(Long id) {
		requestRepository.deleteById(id);
	}
	
	public List<Request> findRequestsByVehicleId(Long vehicleId){
		return requestRepository.findByVehicleId(vehicleId);
	}
}
