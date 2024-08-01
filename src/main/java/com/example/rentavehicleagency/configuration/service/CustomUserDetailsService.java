package com.example.rentavehicleagency.configuration.service;

import com.example.rentavehicleagency.auth.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.example.rentavehicleagency.users.entities.UserEntity;
import com.example.rentavehicleagency.users.repositories.UserRepository;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService{

	private final UserRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String email) {
		UserEntity userEntity = repository.findByEmail(email);
		if (userEntity == null) {
			throw new NotFoundException("UserEntity not found.");
		}
		return org.springframework.security.core.userdetails.User.builder()
				.username(userEntity.getEmail())
				.password(userEntity.getPassword())
				.build();
	}

}
