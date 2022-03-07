package com.cognixia.jump.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.exception.ResourceNotFoundException;
import com.cognixia.jump.model.Customer;
import com.cognixia.jump.model.Vehicle;
import com.cognixia.jump.service.CustomerService;
import com.cognixia.jump.service.VehicleService;

@RequestMapping("/api")
@RestController
public class DealershipController {
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	VehicleService vehicleService;
	
	@GetMapping("/test")
	public ResponseEntity<?> test(){
		return ResponseEntity.status(200).body("You have reached the api");
	}
	
	@PostMapping("/customer")
	public ResponseEntity<?> createCustomer(@RequestBody Customer customer){
		Customer created = customerService.createCustomer(customer);
		return ResponseEntity.status(201).body(created);
	}
	
	@GetMapping("/customer")
	public List<Customer> getAllCustomers(){
		List <Customer> allCustomers = customerService.getAllCustomers();
		return allCustomers;
	}
	
	@GetMapping("/customer/{id}")
	public ResponseEntity<?> getCustomerById(@PathVariable int id) throws ResourceNotFoundException{
		Customer found = customerService.getCustomerById(id);
		return ResponseEntity.status(200).body(found);
	}
	
	@DeleteMapping("/customer/{id}")
	public ResponseEntity<?> deleteCustomerById(@PathVariable int id) throws ResourceNotFoundException{
		Customer deleted = customerService.deleteCustomerById(id);
		return ResponseEntity.status(200).body(deleted);
	}
	
	@PutMapping("/customer")
	public ResponseEntity<?> updateCustomerById(@RequestBody Customer customer) throws ResourceNotFoundException{
		Customer updated = customerService.updateCustomer(customer);
		return ResponseEntity.status(200).body(updated);
	}
	
	@GetMapping("/vehicle")
	public List<Vehicle> getAllVehicles(){
		List<Vehicle> allVehicles = vehicleService.getAllVehicles();
		return allVehicles;
	}
	
}
