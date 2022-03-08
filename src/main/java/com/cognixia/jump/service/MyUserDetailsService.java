package com.cognixia.jump.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cognixia.jump.model.Customer;
import com.cognixia.jump.repository.CustomerRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {
	
	@Autowired
	CustomerRepository repo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<Customer> found = repo.findByEmail(email);
		if(found.isEmpty()) {
			throw new UsernameNotFoundException(email);
		}
		Customer customer = found.get();
		return new MyUserDetails(customer);
	}

}
