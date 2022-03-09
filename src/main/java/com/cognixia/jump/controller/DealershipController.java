package com.cognixia.jump.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.exception.ResourceNotFoundException;
import com.cognixia.jump.model.AuthenticationRequest;
import com.cognixia.jump.model.AuthenticationResponse;
import com.cognixia.jump.model.Customer;
import com.cognixia.jump.model.Vehicle;
import com.cognixia.jump.service.CustomerService;
import com.cognixia.jump.service.VehicleService;
import com.cognixia.jump.util.JwtUtil;

@RequestMapping("/api")
@RestController
public class DealershipController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserDetailsService userDetailsService;

	@Autowired
	JwtUtil jwtUtil;

	@Autowired
	CustomerService customerService;

	@Autowired
	VehicleService vehicleService;

	@GetMapping("/test")
	public ResponseEntity<?> test() {
		return ResponseEntity.status(200).body("You have reached the Lincoln Hawk Auto api");
	}

	@PostMapping("/customer")
	public ResponseEntity<?> createCustomer(@RequestBody Customer customer) {
		Customer created = customerService.createCustomer(customer);
		return ResponseEntity.status(201).body(created);
	}

	@GetMapping("/customer")
	public List<Customer> getAllCustomers() {
		List<Customer> allCustomers = customerService.getAllCustomers();
		return allCustomers;
	}

	@GetMapping("/customer/{id}")
	public ResponseEntity<?> getCustomerById(@PathVariable int id) throws ResourceNotFoundException {
		Customer found = customerService.getCustomerById(id);
		return ResponseEntity.status(200).body(found);
	}

	@DeleteMapping("/customer/{id}")
	public ResponseEntity<?> deleteCustomerById(@PathVariable int id) throws ResourceNotFoundException {
		Customer deleted = customerService.deleteCustomerById(id);
		return ResponseEntity.status(200).body(deleted);
	}

	@PutMapping("/customer")
	public ResponseEntity<?> updateCustomerById(@RequestBody Customer customer) throws ResourceNotFoundException {
		Customer updated = customerService.updateCustomer(customer);
		return ResponseEntity.status(200).body(updated);
	}

	@GetMapping("/vehicle")
	public List<Vehicle> getAllVehicles() {
		List<Vehicle> allVehicles = vehicleService.getAllVehicles();
		return allVehicles;
	}

	@PostMapping("/vehicle")
	public ResponseEntity<?> createVehicle(@RequestBody Vehicle vehicle) {
		Vehicle created = vehicleService.createVehicle(vehicle);
		return ResponseEntity.status(201).body(created);
	}

	@GetMapping("vehicle/{id}")
	public ResponseEntity<?> getVehicleById(@PathVariable String id) throws ResourceNotFoundException {
		Vehicle found = vehicleService.getVehicleById(id);
		return ResponseEntity.status(200).body(found);
	}

	@DeleteMapping("/vehicle/{id}")
	public ResponseEntity<?> deleteVehicleById(@PathVariable String id) throws ResourceNotFoundException {
		Vehicle deleted = vehicleService.deleteByVehicleId(id);
		return ResponseEntity.status(200).body(deleted);
	}

	@PutMapping("/vehicle")
	public ResponseEntity<?> updateVehicleById(@RequestBody Vehicle vehicle) throws ResourceNotFoundException {
		Vehicle updated = vehicleService.updateVehicle(vehicle);
		return ResponseEntity.status(200).body(updated);
	}

	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest request) {
		try {
			authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
		} catch (BadCredentialsException e) {
			throw new BadCredentialsException("Incorrect email or password");
		}
		final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
		final String jwt = jwtUtil.generateTokens(userDetails);
		return ResponseEntity.status(201).body(new AuthenticationResponse(jwt));
	}

	@PatchMapping("/vehicle/{id}/buy")
	public ResponseEntity<?> buyCar(@PathVariable String id) throws ResourceNotFoundException {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String email = userDetails.getUsername();
		Customer customer = customerService.getCustomerByEmail(email);
		Vehicle vehicle = vehicleService.getVehicleById(id);
		Vehicle purchased = vehicleService.purchaseVehicle(customer,vehicle);
		return ResponseEntity.status(200).body(purchased);
	}
	
	@GetMapping("/vehicle/customer/{id}")
	public List<Vehicle> getCarsByCustomer(@PathVariable int id) throws ResourceNotFoundException{
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<Vehicle> customerVehicles=vehicleService.getCarsByCustomer(userDetails, id);
		return customerVehicles;
	}

}
