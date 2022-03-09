package com.cognixia.jump.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cognixia.jump.exception.ResourceNotFoundException;
import com.cognixia.jump.model.Customer;
import com.cognixia.jump.model.Customer.Role;
import com.cognixia.jump.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	CustomerRepository repo;

	@Autowired
	PasswordEncoder encoder;

	public List<Customer> getAllCustomers() {
		return repo.findAll();
	}

	public Customer createCustomer(Customer customer) {

		customer.setId(null);
		customer.setEnabled(true);

		if (customer.getEmail().equals("admin@LincolnHawkAuto.com")) {
			customer.setRole(Role.ROLE_ADMIN);
		} else {
			customer.setRole(Role.ROLE_CUSTOMER);
		}
		customer.setPassword(encoder.encode(customer.getPassword()));
		Customer created = repo.save(customer);
		return created;

	}

	public Customer getCustomerById(int id) throws ResourceNotFoundException {
		Optional<Customer> found = repo.findById(id);
		if (found.isEmpty()) {
			throw new ResourceNotFoundException("Customer with id = " + id + " not found");
		}
		return found.get();
	}

	public Customer deleteCustomerById(int id) throws ResourceNotFoundException {
		Optional<Customer> deleted = repo.findById(id);
		if (deleted.isEmpty()) {
			throw new ResourceNotFoundException("Customer with id = " + id + " not found");
		}
		repo.deleteById(id);
		return deleted.get();
	}

	public Customer updateCustomer(Customer customer) throws ResourceNotFoundException {
		Optional<Customer> found = repo.findById(customer.getId());
		if (found.isEmpty()) {
			throw new ResourceNotFoundException("Customer with id = " + customer.getId() + " not found");
		}
		Customer updated = found.get();
		updated.setEmail(customer.getEmail());
		updated.setPassword(customer.getPassword());
		updated.setName(customer.getName());
		repo.save(updated);
		return updated;
	}

	public Customer getCustomerByEmail(String email) throws ResourceNotFoundException {
		Optional<Customer> found = repo.findByEmail(email);
		if (found.isEmpty()) {
			throw new ResourceNotFoundException("Customer with email = " + email + " not found");
		}
		return found.get();
	}

}
