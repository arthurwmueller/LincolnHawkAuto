package com.cognixia.jump.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.cognixia.jump.exception.ResourceNotFoundException;
import com.cognixia.jump.model.Customer;
import com.cognixia.jump.model.Customer.Role;
import com.cognixia.jump.model.Vehicle;
import com.cognixia.jump.repository.VehicleRepository;

@Service
public class VehicleService {
	
	@Autowired
	VehicleRepository repo;
	
	@Autowired
	CustomerService customerService;

	public List<Vehicle> getAllVehicles() {
		
		return repo.findAll();
	}

	public Vehicle createVehicle(Vehicle vehicle) {
		vehicle.setId(null);
		vehicle.setCustomer(null);
		vehicle.setSold(false);
		Vehicle created = repo.save(vehicle);
		return created;
	}

	public Vehicle getVehicleById(String id) throws ResourceNotFoundException {
		Optional<Vehicle> found = repo.findById(id);
		if(found.isEmpty()) {
			throw new ResourceNotFoundException("Vehicle with id = "+id+" not found");
		}
		return found.get();
	}

	public Vehicle deleteByVehicleId(String id) throws ResourceNotFoundException {
		Optional<Vehicle> found = repo.findById(id);
		if(found.isEmpty()) {
			throw new ResourceNotFoundException("Vehicle with id = "+id+" not found");
		}
		repo.deleteById(id);
		return found.get();
	}

	public Vehicle updateVehicle(Vehicle vehicle) throws ResourceNotFoundException {
		Optional<Vehicle> found = repo.findById(vehicle.getId());
		if(found.isEmpty()) {
			throw new ResourceNotFoundException("Vehicle with id = "+vehicle.getId()+" not found");
		}
		Vehicle updated=found.get();
		updated.setMake(vehicle.getMake());
		updated.setModel(vehicle.getModel());
		updated.setTrim(vehicle.getTrim());
		updated.setColor(vehicle.getColor());
		updated.setYear(vehicle.getYear());
		updated.setBodyType(vehicle.getBodyType());
		updated.setMpg(vehicle.getMpg());
		updated.setAllWheelDrive(vehicle.getAllWheelDrive());
		updated.setPrice(vehicle.getPrice());
		updated.setHorsepower(vehicle.getHorsepower());
		updated.setTowing(vehicle.getTowing());
		updated.setSunroof(vehicle.getSunroof());
		updated.setCapacity(vehicle.getCapacity());
		updated.setDescription(vehicle.getDescription());
		updated.setImage(vehicle.getImage());
		updated.setLocation(vehicle.getLocation());
		updated.setSold(vehicle.getSold());
		updated.setCustomer(vehicle.getCustomer());
		repo.save(updated);
		return updated;
	}

	public Vehicle purchaseVehicle(Customer customer, Vehicle vehicle) throws ResourceNotFoundException {
		if(vehicle.getSold()) {
			throw new ResourceNotFoundException("Sorry, this car has already been purchased");
		}
		vehicle.setSold(true);
		vehicle.setCustomer(customer.getId());
		repo.save(vehicle);
		return vehicle;
	}

	public List<Vehicle> getCarsByCustomer(UserDetails userDetails, int id) throws ResourceNotFoundException {
		String email = userDetails.getUsername();
		Customer customer = customerService.getCustomerByEmail(email);
		if(customer.getId()==id||customer.getRole()==Role.ROLE_ADMIN) {
			return repo.findByCustomer(id);
		}
		return null;
	}

}
