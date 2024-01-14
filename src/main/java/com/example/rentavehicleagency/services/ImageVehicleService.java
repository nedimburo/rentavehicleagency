package com.example.rentavehicleagency.services;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.rentavehicleagency.models.ImageVehicle;
import com.example.rentavehicleagency.models.Vehicle;
import com.example.rentavehicleagency.repositories.ImageVehicleRepository;

@Service
public class ImageVehicleService {

	@Autowired
	private ImageVehicleRepository imageVehicleRepository;
	
	@Autowired
	private FileStorageService fileStorageService;
	
	public void saveListImageVehicle(List<MultipartFile> images, Vehicle vehicle) {
		for (int i=0; i<images.size(); i++) {
			try {
				ImageVehicle imageVehicle=new ImageVehicle();
				String imageUrl=fileStorageService.storeVehicleImage(images.get(i));
				imageVehicle.setImageName(imageUrl);
				imageVehicle.setVehicle(vehicle);
				imageVehicleRepository.save(imageVehicle);
			}catch(IOException e) {
				throw new RuntimeException("Error during file upload: "+e.getMessage());
			}
		}
	}
	
	public List<ImageVehicle> getAllImageVehicles(){
		return imageVehicleRepository.findAll();
	}
	
	public List<ImageVehicle> getVehicleImages(Long id){
		return imageVehicleRepository.findByVehicleId(id);
	}
	
	public void deleteImageVehicleById(Long id) {
		imageVehicleRepository.deleteById(id);
	}
}
