package com.example.rentavehicleagency.vehicles.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.example.rentavehicleagency.businesses.entities.BusinessEntity;
import com.example.rentavehicleagency.businesses.services.BusinessService;
import com.example.rentavehicleagency.damages.services.DamageService;
import com.example.rentavehicleagency.requests.services.RequestService;
import com.example.rentavehicleagency.vehicleImages.services.VehicleImageService;
import com.example.rentavehicleagency.vehicles.Vehicle;
import com.example.rentavehicleagency.vehicles.entities.*;
import com.example.rentavehicleagency.vehicles.payloads.VehicleRequestDto;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

	private final VehicleRepository repository;
	private final DamageService damageService;
	private final VehicleImageService vehicleImageService;
	private final RequestService requestService;
	private final BusinessService businessService;

	@Transactional
	public ResponseEntity<?> addVehicle(VehicleRequestDto vehicleRequestDto) {
		BusinessEntity business = businessService.findBusinessByName(vehicleRequestDto.getBusinessName());
		VehicleEntity vehicle = new VehicleEntity();
		vehicle.setBrand(vehicleRequestDto.getBrand());
		vehicle.setModelName(vehicleRequestDto.getModelName());
		vehicle.setModelYear(vehicleRequestDto.getModelYear());
		vehicle.setRegistrationPlate(vehicleRequestDto.getRegistrationPlate());
		vehicle.setEngineDisplacement(vehicleRequestDto.getEngineDisplacement());
		vehicle.setHorsepower(vehicleRequestDto.getHorsepower());
		vehicle.setFuel(vehicleRequestDto.getFuel());
		vehicle.setTransmission(vehicleRequestDto.getTransmission());
		vehicle.setColor(vehicleRequestDto.getColor());
		vehicle.setType(vehicleRequestDto.getType());
		vehicle.setBodyShape(vehicleRequestDto.getBodyShape());
		vehicle.setPrice(vehicleRequestDto.getPrice());
		vehicle.setStatus(VehicleStatus.AVAILABLE);
		vehicle.setAddedDate(LocalDate.now());
		vehicle.setBusinessEntity(business);
		repository.save(vehicle);
		VehicleEntity newVehicle = this.findVehicleByRegistrationPlate(vehicleRequestDto.getRegistrationPlate());
		vehicleImageService.saveListImageVehicle(vehicleRequestDto.getImages(), newVehicle);
		return new ResponseEntity<>("Vehicle has been successfully added.", HttpStatus.OK);
	}
	
	public VehicleEntity findVehicleByRegistrationPlate(String registrationPlate) {
		return repository.findByRegistrationPlate(registrationPlate);
	}
	
	public List<VehicleEntity> getAllVehicles(){
		return repository.findAll();
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
			newVehicleDisplay.setTransmission(String.valueOf(allVehicleEntities.get(i).getTransmission()));
			newVehicleDisplay.setFuel(String.valueOf(allVehicleEntities.get(i).getFuel()));
			newVehicleDisplay.setBodyType(String.valueOf(allVehicleEntities.get(i).getBodyShape()));
			newVehicleDisplay.setStatus(String.valueOf(allVehicleEntities.get(i).getStatus()));
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
		return repository.findById(id).orElse(null);
	}
	
	public void setVehicleStatus(VehicleEntity targetVehicleEntity, String status) {
		VehicleEntity existingVehicleEntity = repository.findById(targetVehicleEntity.getId()).orElse(null);
		if (existingVehicleEntity != null) {
			existingVehicleEntity.setStatus(VehicleStatus.valueOf(status));
			repository.save(existingVehicleEntity);
		}
	}
	
	public List<VehicleEntity> getAllVehiclesByBusinessId(Long businessId){
		return repository.findByBusinessEntityId(businessId);
	}

	@Transactional
	public ResponseEntity<?> removeVehicle(Long id) {
		VehicleEntity vehicleEntity = repository.findById(id).orElse(null);
		List<DamageEntity> damagesVehicle = damageService.getDamagesByVehicleId(vehicleEntity.getId());
		List<VehicleImageEntity> imagesVehicle = vehicleImageService.getVehicleImages(vehicleEntity.getId());
		List<RequestEntity> requestsVehicle = requestService.findRequestsByVehicleId(vehicleEntity.getId());
        for (DamageEntity damageEntity : damagesVehicle) {
            damageService.deleteDamageById(damageEntity.getId());
        }
        for (VehicleImageEntity vehicleImageEntity : imagesVehicle) {
            vehicleImageService.deleteImageVehicleById(vehicleImageEntity.getId());
        }
        for (RequestEntity requestEntity : requestsVehicle) {
            requestService.deleteRequestById(requestEntity.getId());
        }
		repository.deleteById(id);
		return new ResponseEntity<>("Vehicle has been successfully removed.", HttpStatus.OK);
	}
}
