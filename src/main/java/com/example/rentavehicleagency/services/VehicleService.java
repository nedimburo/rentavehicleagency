package com.example.rentavehicleagency.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.example.rentavehicleagency.damages.services.DamageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.rentavehicleagency.dto.VehicleDisplayDto;
import com.example.rentavehicleagency.damages.entities.DamageEntity;
import com.example.rentavehicleagency.models.ImageVehicle;
import com.example.rentavehicleagency.models.Request;
import com.example.rentavehicleagency.models.Vehicle;
import com.example.rentavehicleagency.repositories.VehicleRepository;

@Service
public class VehicleService {

	@Autowired
	private VehicleRepository vehicleRepository;
	
	@Autowired
	private DamageService damageService;
	
	@Autowired
	private ImageVehicleService imageVehicleService;
	
	@Autowired
	private RequestService requestService;
	
	public void saveVehicle(Vehicle vehicle) {
		vehicle.setStatus("AVAILABLE");
		vehicle.setAddedDate(LocalDate.now());
		vehicleRepository.save(vehicle);
	}
	
	public Vehicle findVehicleByRegistrationPlate(String registrationPlate) {
		return vehicleRepository.findByRegistrationPlate(registrationPlate);
	}
	
	public List<Vehicle> getAllVehicles(){
		return vehicleRepository.findAll();
	}
	
	public List<VehicleDisplayDto> getAllVehiclesWithImages(List<Vehicle> allVehicles, List<ImageVehicle> allVehicleImages){
		List<VehicleDisplayDto> newList=new ArrayList<>();
		for (int i=0; i<allVehicles.size(); i++) {
			VehicleDisplayDto newVehicleDisplay=new VehicleDisplayDto();
			newVehicleDisplay.setId(allVehicles.get(i).getId());
			newVehicleDisplay.setBrand(allVehicles.get(i).getBrand());
			newVehicleDisplay.setModelName(allVehicles.get(i).getModelName());
			newVehicleDisplay.setModelYear(allVehicles.get(i).getModelYear());
			newVehicleDisplay.setHorsepower(allVehicles.get(i).getHorsepower());
			newVehicleDisplay.setTransmission(allVehicles.get(i).getTransmission());
			newVehicleDisplay.setFuel(allVehicles.get(i).getFuel());
			newVehicleDisplay.setBodyType(allVehicles.get(i).getBodyShape());
			newVehicleDisplay.setStatus(allVehicles.get(i).getStatus());
			newVehicleDisplay.setPrice(allVehicles.get(i).getPrice());
			for (int j=0; j<allVehicleImages.size(); j++) {
				if (allVehicles.get(i).getId()==allVehicleImages.get(j).getVehicle().getId()) {
					newVehicleDisplay.setImageName(allVehicleImages.get(j).getImageName());
					newList.add(newVehicleDisplay);
					break;
				}
			}
		}
		return newList;
	}
	
	public Vehicle getVehicleById(Long id) {
		return vehicleRepository.findById(id).orElse(null);
	}
	
	public void setVehicleStatus(Vehicle targetVehicle, String status) {
		Vehicle existingVehicle=vehicleRepository.findById(targetVehicle.getId()).orElse(null);
		if (existingVehicle!=null) {
			existingVehicle.setStatus(status);
			vehicleRepository.save(existingVehicle);
		}
	}
	
	public List<Vehicle> getAllVehiclesByBusinessId(Long businessId){
		return vehicleRepository.findByBusinessId(businessId);
	}
	
	public void removeVehicle(Long id) {
		Vehicle vehicle=vehicleRepository.findById(id).orElse(null);
		List<DamageEntity> damagesVehicle=damageService.getDamagesByVehicleId(vehicle.getId());
		List<ImageVehicle> imagesVehicle=imageVehicleService.getVehicleImages(vehicle.getId());
		List<Request> requestsVehicle=requestService.findRequestsByVehicleId(vehicle.getId());
		for (int i=0; i<damagesVehicle.size(); i++) {
			damageService.deleteDamageById(damagesVehicle.get(i).getId());
		}
		for (int i=0; i<imagesVehicle.size(); i++) {
			imageVehicleService.deleteImageVehicleById(imagesVehicle.get(i).getId());
		}
		for (int i=0; i<requestsVehicle.size(); i++) {
			requestService.deleteRequestById(requestsVehicle.get(i).getId());
		}
		vehicleRepository.deleteById(id);
	}
}
