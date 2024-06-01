package com.example.rentavehicleagency.configuration.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileStorageService {

	@Value("${file.upload-dir.user-images}")
	private String userImagesUploadDir;
	
	@Value("${file.upload-dir.vehicle-images}")
	private String vehicleImagesUploadDir;
	
	public String storeUserImage(MultipartFile file) throws IOException{
		return storeFile(file, userImagesUploadDir);
	}
	
	public String storeVehicleImage(MultipartFile file) throws IOException{
		return storeFile(file, vehicleImagesUploadDir);
	}
	
	private String storeFile(MultipartFile file, String uploadDir) throws IOException{
		String fileName=generateUniqueFileName(file.getOriginalFilename());
		try (OutputStream os=new FileOutputStream(Paths.get(uploadDir).resolve(fileName).toString())){
			os.write(file.getBytes());
		}
		return fileName;
	}
	
	private String generateUniqueFileName(String originalFileName) {
		String extension=extractFileExtension(originalFileName);
		String uniqueFileName=UUID.randomUUID().toString()+"."+extension;
		return uniqueFileName;
	}
	
	private String extractFileExtension(String fileName) {
		int dotIndex=fileName.lastIndexOf('.');
		if (dotIndex>0) {
			return fileName.substring(dotIndex+1);
		}
		return "";
	}
}
