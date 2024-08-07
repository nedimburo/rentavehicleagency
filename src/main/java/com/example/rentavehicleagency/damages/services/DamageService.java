package com.example.rentavehicleagency.damages.services;

import java.time.LocalDateTime;
import java.util.List;

import com.example.rentavehicleagency.damages.Damage;
import com.example.rentavehicleagency.damages.entities.DamageEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.example.rentavehicleagency.damages.repositories.DamageRepository;

@Slf4j
@Getter
@Service
@RequiredArgsConstructor
public class DamageService implements Damage {

	private final DamageRepository damageRepository;
	
	public DamageEntity getDamageById(Long id) {
		return damageRepository.findById(id).orElse(null);
	}
	
	public void saveDamage(DamageEntity damageEntity) {
		damageEntity.setReportDate(LocalDateTime.now());
		damageRepository.save(damageEntity);
	}
	
	public List<DamageEntity> getDamagesByVehicleId(Long vehicleId) {
		return damageRepository.findByVehicleEntityId(vehicleId);
	}
	
	public DamageEntity getActiveDamage(Long vehicleId) {
		List<DamageEntity> allDamageEntities =getDamagesByVehicleId(vehicleId);
		DamageEntity activeDamageEntity =new DamageEntity();
		for (int i = 0; i< allDamageEntities.size(); i++) {
			if (allDamageEntities.get(i).getFixDate()==null) {
				activeDamageEntity = allDamageEntities.get(i);
				break;
			}
		}
		return activeDamageEntity;
	}
	
	public void resolveDamage(DamageEntity damageEntity) {
		damageRepository.save(damageEntity);
	}
	
	public List<DamageEntity> getDamagesByEmployeeId(Long employeeId){
		return damageRepository.findByEmployeeEntityId(employeeId);
	}
	
	public void deleteDamageById(Long id) {
		damageRepository.deleteById(id);
	}
}
