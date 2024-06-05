package com.example.rentavehicleagency.clients.services;

import com.example.rentavehicleagency.clients.entities.ClientEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.rentavehicleagency.users.entities.UserEntity;
import com.example.rentavehicleagency.clients.repositories.ClientRepository;

@Service
public class ClientService {

	@Autowired
	private ClientRepository clientRepository;
	
	public void assignUserToClient(UserEntity userEntity) {
		ClientEntity clientEntity =new ClientEntity();
		clientEntity.setIsConfirmed(0);
		clientEntity.setUserEntity(userEntity);
		clientRepository.save(clientEntity);
	}
	
	public ClientEntity findClientByUserId(Long userId) {
		return clientRepository.findByUserEntityId(userId);
	}
	
	public void addClientInformation(ClientEntity updatedClientEntity) {
		ClientEntity existingClientEntity =clientRepository.findByUserEntityId(updatedClientEntity.getUserEntity().getId());
		if (existingClientEntity !=null) {
			existingClientEntity.setAddress(updatedClientEntity.getAddress());
			existingClientEntity.setContactNumber(updatedClientEntity.getContactNumber());
			existingClientEntity.setJmbg(updatedClientEntity.getJmbg());
			existingClientEntity.setBankInformation(updatedClientEntity.getBankInformation());
			existingClientEntity.setLicenseNumber(updatedClientEntity.getLicenseNumber());
			existingClientEntity.setIsConfirmed(1);
			clientRepository.save(existingClientEntity);
		}
	}
}
