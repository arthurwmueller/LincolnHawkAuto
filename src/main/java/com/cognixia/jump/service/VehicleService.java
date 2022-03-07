package com.cognixia.jump.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognixia.jump.model.Vehicle;
import com.cognixia.jump.repository.VehicleRepository;

@Service
public class VehicleService {
	
	@Autowired
	VehicleRepository repo;

	public List<Vehicle> getAllVehicles() {
		
		return repo.findAll();
	}

}
