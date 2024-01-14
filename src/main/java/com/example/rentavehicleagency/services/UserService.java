package com.example.rentavehicleagency.services;


import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.rentavehicleagency.models.User;
import com.example.rentavehicleagency.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private FileStorageService fileStorageService;
	
	public void registerUser(User user) {
		user.setProfileImage("default_user_image.jpg");
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRole("USER");
		userRepository.save(user);
		clientService.assignUserToClient(user);
	}
	
	
	public User getUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	public User getUserByNickname(String nickname) {
		return userRepository.findByNickname(nickname);
	}
	
	public User getUserById(Long id) {
		return userRepository.findById(id).orElse(null);
	}
	
	public void setProfileImage(User user, MultipartFile image) {
		try {
			String imageUrl=fileStorageService.storeUserImage(image);
			user.setProfileImage(imageUrl);
			userRepository.save(user);
		}catch(IOException e) {
			throw new RuntimeException("Error during file upload: "+e.getMessage());
		}
	}
	
	public void saveUserInstance(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
	}
	
	public void deleteUserById(Long id) {
		userRepository.deleteById(id);
	}
}
