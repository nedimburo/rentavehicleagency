package com.example.rentavehicleagency.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.rentavehicleagency.models.Damage;
import com.example.rentavehicleagency.repositories.DamageRepository;

@Service
public class DamageService {

	@Autowired
	private DamageRepository damageRepository;
	
	public Damage getDamageById(Long id) {
		return damageRepository.findById(id).orElse(null);
	}
	
	public void saveDamage(Damage damage) {
		damage.setReportDate(LocalDateTime.now());
		damageRepository.save(damage);
	}
	
	public List<Damage> getDamagesByVehicleId(Long vehicleId) {
		return damageRepository.findByVehicleId(vehicleId);
	}
	
	public Damage getActiveDamage(Long vehicleId) {
		List<Damage> allDamages=getDamagesByVehicleId(vehicleId);
		Damage activeDamage=new Damage();
		for (int i=0; i<allDamages.size(); i++) {
			if (allDamages.get(i).getFixDate()==null) {
				activeDamage=allDamages.get(i);
				break;
			}
		}
		return activeDamage;
	}
	
	public void resolveDamage(Damage damage) {
		damageRepository.save(damage);
	}
	
	public List<Damage> getDamagesByEmployeeId(Long employeeId){
		return damageRepository.findByEmployeeId(employeeId);
	}
	
	public void deleteDamageById(Long id) {
		damageRepository.deleteById(id);
	}
}
