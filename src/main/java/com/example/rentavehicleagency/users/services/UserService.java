package com.example.rentavehicleagency.users.services;


import java.io.IOException;
import java.time.LocalDate;

import com.example.rentavehicleagency.clients.services.ClientService;
import com.example.rentavehicleagency.configuration.service.FileStorageService;
import com.example.rentavehicleagency.configuration.service.JwtHelper;
import com.example.rentavehicleagency.users.User;
import com.example.rentavehicleagency.users.entities.RoleType;
import com.example.rentavehicleagency.users.entities.UserEntity;
import com.example.rentavehicleagency.auth.payloads.LoginRequestDto;
import com.example.rentavehicleagency.auth.payloads.LoginResponseDto;
import com.example.rentavehicleagency.auth.payloads.RegistrationRequestDto;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.rentavehicleagency.users.repositories.UserRepository;

@Slf4j
@Getter
@Service
@RequiredArgsConstructor
public class UserService implements User {

	private final UserRepository repository;
	private final PasswordEncoder passwordEncoder;
	private final ClientService clientService;
	private final FileStorageService fileStorageService;
	private final AuthenticationManager authenticationManager;

	@Transactional
	public ResponseEntity<?> registerUser(RegistrationRequestDto registrationRequestDto) {
		UserEntity user = new UserEntity();
		user.setFirstName(registrationRequestDto.getFirstName());
		user.setLastName(registrationRequestDto.getLastName());
		user.setEmail(registrationRequestDto.getEmail());
		user.setPassword(passwordEncoder.encode(registrationRequestDto.getPassword()));
		user.setNickname(registrationRequestDto.getNickname());
		user.setGender(registrationRequestDto.getGender());
		final LocalDate dt = LocalDate.parse(registrationRequestDto.getBirthDate());
		user.setBirthDate(dt);
		user.setRole(RoleType.USER);
		user.setProfileImage("default_user_image.jpg");
		repository.save(user);
		clientService.assignUserToClient(user);
		return new ResponseEntity<>("User has been successfully registered.", HttpStatus.OK);
	}

	@Transactional
	public ResponseEntity<LoginResponseDto> loginUser(LoginRequestDto loginRequestDto){
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDto.getEmail(), loginRequestDto.getPassword()));
		String token = JwtHelper.generateToken(loginRequestDto.getEmail());
		return ResponseEntity.ok(new LoginResponseDto(loginRequestDto.getEmail(), token));
	}

	public UserEntity getUserByEmail(String email) {
		return repository.findByEmail(email);
	}
	
	public UserEntity getUserByNickname(String nickname) {
		return repository.findByNickname(nickname);
	}
	
	public UserEntity getUserById(Long id) {
		return repository.findById(id).orElse(null);
	}
	
	public void setProfileImage(UserEntity userEntity, MultipartFile image) {
		try {
			String imageUrl=fileStorageService.storeUserImage(image);
			userEntity.setProfileImage(imageUrl);
			repository.save(userEntity);
		}catch(IOException e) {
			throw new RuntimeException("Error during file upload: "+e.getMessage());
		}
	}
	
	public void saveUserInstance(UserEntity userEntity) {
		userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
		repository.save(userEntity);
	}
	
	public void deleteUserById(Long id) {
		repository.deleteById(id);
	}
}
