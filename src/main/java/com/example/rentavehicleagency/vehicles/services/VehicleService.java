package com.example.rentavehicleagency.vehicles.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.example.rentavehicleagency.damages.services.DamageService;
import com.example.rentavehicleagency.requests.services.RequestService;
import com.example.rentavehicleagency.vehicleImages.services.VehicleImageService;
import com.example.rentavehicleagency.vehicles.Vehicle;
import com.example.rentavehicleagency.vehicles.entities.VehicleEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.example.rentavehicleagency.vehicles.payloads.VehicleDisplayDto;
import com.example.rentavehicleagency.damages.entities.DamageEntity;
import com.example.rentavehicleagency.vehicleImages.entities.VehicleImageEntity;
import com.example.rentavehicleagency.requests.entities.RequestEntity;
import com.example.rentavehicleagency.vehicles.repositories.VehicleRepository;

@Slf4j
@Getter
@Service
@RequiredArgsConstructor
public class VehicleService implements Vehicle {

	private final VehicleRepository vehicleRepository;
	
	private final DamageService damageService;
	
	private final VehicleImageService vehicleImageService;
	
	private final RequestService requestService;
	
	public void saveVehicle(VehicleEntity vehicleEntity) {
		vehicleEntity.setStatus("AVAILABLE");
		vehicleEntity.setAddedDate(LocalDate.now());
		vehicleRepository.save(vehicleEntity);
	}
	
	public VehicleEntity findVehicleByRegistrationPlate(String registrationPlate) {
		return vehicleRepository.findByRegistrationPlate(registrationPlate);
	}
	
	public List<VehicleEntity> getAllVehicles(){
		return vehicleRepository.findAll();
	}
	
	public List<VehicleDisplayDto> getAllVehiclesWithImages(List<VehicleEntity> allVehicleEntities, List<VehicleImageEntity> allVehicleImages){
		List<VehicleDisplayDto> newList=new ArrayList<>();
		for (int i = 0; i< allVehicleEntities.size(); i++) {
			VehicleDisplayDto newVehicleDisplay=new VehicleDisplayDto();
			newVehicleDisplay.setId(allVehicleEntities.get(i).getId());
			newVehicleDisplay.setBrand(allVehicleEntities.get(i).getBrand());
			newVehicleDisplay.setModelName(allVehicleEntities.get(i).getModelName());
			newVehicleDisplay.setModelYear(allVehicleEntities.get(i).getModelYear());
			newVehicleDisplay.setHorsepower(allVehicleEntities.get(i).getHorsepower());
			newVehicleDisplay.setTransmission(allVehicleEntities.get(i).getTransmission());
			newVehicleDisplay.setFuel(allVehicleEntities.get(i).getFuel());
			newVehicleDisplay.setBodyType(allVehicleEntities.get(i).getBodyShape());
			newVehicleDisplay.setStatus(allVehicleEntities.get(i).getStatus());
			newVehicleDisplay.setPrice(allVehicleEntities.get(i).getPrice());
			for (int j=0; j<allVehicleImages.size(); j++) {
				if (allVehicleEntities.get(i).getId()==allVehicleImages.get(j).getVehicleEntity().getId()) {
					newVehicleDisplay.setImageName(allVehicleImages.get(j).getImageName());
					newList.add(newVehicleDisplay);
					break;
				}
			}
		}
		return newList;
	}
	
	public VehicleEntity getVehicleById(Long id) {
		return vehicleRepository.findById(id).orElse(null);
	}
	
	public void setVehicleStatus(VehicleEntity targetVehicleEntity, String status) {
		VehicleEntity existingVehicleEntity =vehicleRepository.findById(targetVehicleEntity.getId()).orElse(null);
		if (existingVehicleEntity !=null) {
			existingVehicleEntity.setStatus(status);
			vehicleRepository.save(existingVehicleEntity);
		}
	}
	
	public List<VehicleEntity> getAllVehiclesByBusinessId(Long businessId){
		return vehicleRepository.findByBusinessEntityId(businessId);
	}
	
	public void removeVehicle(Long id) {
		VehicleEntity vehicleEntity =vehicleRepository.findById(id).orElse(null);
		List<DamageEntity> damagesVehicle=damageService.getDamagesByVehicleId(vehicleEntity.getId());
		List<VehicleImageEntity> imagesVehicle= vehicleImageService.getVehicleImages(vehicleEntity.getId());
		List<RequestEntity> requestsVehicle=requestService.findRequestsByVehicleId(vehicleEntity.getId());
		for (int i=0; i<damagesVehicle.size(); i++) {
			damageService.deleteDamageById(damagesVehicle.get(i).getId());
		}
		for (int i=0; i<imagesVehicle.size(); i++) {
			vehicleImageService.deleteImageVehicleById(imagesVehicle.get(i).getId());
		}
		for (int i=0; i<requestsVehicle.size(); i++) {
			requestService.deleteRequestById(requestsVehicle.get(i).getId());
		}
		vehicleRepository.deleteById(id);
	}
}
