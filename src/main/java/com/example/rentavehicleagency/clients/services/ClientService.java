package com.example.rentavehicleagency.clients.services;

import com.example.rentavehicleagency.clients.Client;
import com.example.rentavehicleagency.clients.entities.ClientEntity;
import com.example.rentavehicleagency.clients.payloads.ClientRequestDto;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.rentavehicleagency.users.entities.UserEntity;
import com.example.rentavehicleagency.clients.repositories.ClientRepository;

@Slf4j
@Getter
@Service
@RequiredArgsConstructor
public class ClientService implements Client {

	private final ClientRepository repository;
	
	public void assignUserToClient(UserEntity userEntity) {
		ClientEntity client = new ClientEntity();
		client.setIsConfirmed(0);
		client.setUserEntity(userEntity);
		repository.save(client);
	}
	
	public ClientEntity findClientByUserId(Long userId) {
		return repository.findByUserEntityId(userId);
	}

	@Transactional
	public ResponseEntity<?> addClientInformation(ClientRequestDto clientRequestDto) {
		ClientEntity existingClientEntity = repository.findByUserEntityId(clientRequestDto.getUserId());
		if (existingClientEntity != null) {
			existingClientEntity.setAddress(clientRequestDto.getAddress());
			existingClientEntity.setContactNumber(clientRequestDto.getContactNumber());
			existingClientEntity.setJmbg(clientRequestDto.getJmbg());
			existingClientEntity.setBankInformation(clientRequestDto.getBankInformation());
			existingClientEntity.setLicenseNumber(clientRequestDto.getLicenseNumber());
			existingClientEntity.setIsConfirmed(1);
			repository.save(existingClientEntity);
		}
		return new ResponseEntity<>("Client information has been successfully added.", HttpStatus.OK);
	}
}
