package com.example.rentavehicleagency.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.rentavehicleagency.models.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{
	Client findByUserId(Long userId);
}
