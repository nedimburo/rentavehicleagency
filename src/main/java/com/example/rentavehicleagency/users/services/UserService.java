package com.example.rentavehicleagency.users.services;


import java.io.IOException;

import com.example.rentavehicleagency.clients.services.ClientService;
import com.example.rentavehicleagency.configuration.service.FileStorageService;
import com.example.rentavehicleagency.users.User;
import com.example.rentavehicleagency.users.entities.UserEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.rentavehicleagency.users.repositories.UserRepository;

@Slf4j
@Getter
@Service
@RequiredArgsConstructor
public class UserService implements User {

	private final UserRepository userRepository;
	
	private final PasswordEncoder passwordEncoder;
	
	private final ClientService clientService;
	
	private final FileStorageService fileStorageService;
	
	public void registerUser(UserEntity userEntity) {
		userEntity.setProfileImage("default_user_image.jpg");
		userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
		userEntity.setRole("USER");
		userRepository.save(userEntity);
		clientService.assignUserToClient(userEntity);
	}
	
	
	public UserEntity getUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	public UserEntity getUserByNickname(String nickname) {
		return userRepository.findByNickname(nickname);
	}
	
	public UserEntity getUserById(Long id) {
		return userRepository.findById(id).orElse(null);
	}
	
	public void setProfileImage(UserEntity userEntity, MultipartFile image) {
		try {
			String imageUrl=fileStorageService.storeUserImage(image);
			userEntity.setProfileImage(imageUrl);
			userRepository.save(userEntity);
		}catch(IOException e) {
			throw new RuntimeException("Error during file upload: "+e.getMessage());
		}
	}
	
	public void saveUserInstance(UserEntity userEntity) {
		userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
		userRepository.save(userEntity);
	}
	
	public void deleteUserById(Long id) {
		userRepository.deleteById(id);
	}
}
