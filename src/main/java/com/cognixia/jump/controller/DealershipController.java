package com.cognixia.jump.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.service.CustomerService;
import com.cognixia.jump.service.VehicleService;

@RequestMapping("/api")
@RestController
public class DealershipController {
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	VehicleService vehicleService;
	
}
