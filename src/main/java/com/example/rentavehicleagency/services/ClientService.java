package com.example.rentavehicleagency.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.rentavehicleagency.models.Client;
import com.example.rentavehicleagency.models.User;
import com.example.rentavehicleagency.repositories.ClientRepository;

@Service
public class ClientService {

	@Autowired
	private ClientRepository clientRepository;
	
	public void assignUserToClient(User user) {
		Client client=new Client();
		client.setIsConfirmed(0);
		client.setUser(user);
		clientRepository.save(client);
	}
	
	public Client findClientByUserId(Long userId) {
		return clientRepository.findByUserId(userId);
	}
	
	public void addClientInformation(Client updatedClient) {
		Client existingClient=clientRepository.findByUserId(updatedClient.getUser().getId());
		if (existingClient!=null) {
			existingClient.setAddress(updatedClient.getAddress());
			existingClient.setContactNumber(updatedClient.getContactNumber());
			existingClient.setJmbg(updatedClient.getJmbg());
			existingClient.setBankInformation(updatedClient.getBankInformation());
			existingClient.setLicenseNumber(updatedClient.getLicenseNumber());
			existingClient.setIsConfirmed(1);
			clientRepository.save(existingClient);
		}
	}
}
