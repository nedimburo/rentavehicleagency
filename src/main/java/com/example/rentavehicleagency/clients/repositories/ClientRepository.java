package com.example.rentavehicleagency.clients.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.rentavehicleagency.clients.entities.ClientEntity;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, Long>{
	ClientEntity findByUserId(Long userId);
}
