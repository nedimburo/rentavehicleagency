package com.example.rentavehicleagency.vehicleImages.services;

import java.io.IOException;
import java.util.List;

import com.example.rentavehicleagency.configuration.service.FileStorageService;
import com.example.rentavehicleagency.vehicleImages.VehicleImage;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.rentavehicleagency.vehicleImages.entities.VehicleImageEntity;
import com.example.rentavehicleagency.vehicles.entities.VehicleEntity;
import com.example.rentavehicleagency.vehicleImages.repositories.VehicleImageRepository;

@Slf4j
@Getter
@Service
@RequiredArgsConstructor
public class VehicleImageService implements VehicleImage {

	private final VehicleImageRepository vehicleImageRepository;
	
	private final FileStorageService fileStorageService;
	
	public void saveListImageVehicle(List<MultipartFile> images, VehicleEntity vehicleEntity) {
		for (int i=0; i<images.size(); i++) {
			try {
				VehicleImageEntity vehicleImageEntity =new VehicleImageEntity();
				String imageUrl=fileStorageService.storeVehicleImage(images.get(i));
				vehicleImageEntity.setImageName(imageUrl);
				vehicleImageEntity.setVehicleEntity(vehicleEntity);
				vehicleImageRepository.save(vehicleImageEntity);
			}catch(IOException e) {
				throw new RuntimeException("Error during file upload: "+e.getMessage());
			}
		}
	}
	
	public List<VehicleImageEntity> getAllImageVehicles(){
		return vehicleImageRepository.findAll();
	}
	
	public List<VehicleImageEntity> getVehicleImages(Long id){
		return vehicleImageRepository.findByVehicleEntityId(id);
	}
	
	public void deleteImageVehicleById(Long id) {
		vehicleImageRepository.deleteById(id);
	}
}
