package com.example.rentavehicleagency.configuration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.rentavehicleagency.users.entities.UserEntity;
import com.example.rentavehicleagency.users.repositories.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity userEntity =userRepository.findByEmail(username);
		if (userEntity ==null) {
			throw new UsernameNotFoundException("UserEntity not found.");
		}
		return new CustomUserDetails(userEntity);
	}

}
