package com.cognixia.jump.controller;


import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.cognixia.jump.model.Customer;
import com.cognixia.jump.model.Customer.Role;
import com.cognixia.jump.service.CustomerService;
import com.cognixia.jump.service.VehicleService;
import com.cognixia.jump.util.JwtUtil;

@ExtendWith(SpringExtension.class)
@WebMvcTest(DealershipController.class)
public class DealershipControllerTest {

	private final String STARTING_URI = "http://localhost:8080/api";
	@Autowired
	private MockMvc mvc;
	@MockBean
	private CustomerService customerService;
	@MockBean
	private VehicleService vehicleService;
	@InjectMocks
	private DealershipController controller;
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserDetailsService userDetailsService;

	@MockBean
	JwtUtil jwtUtil;

	@Test
	void testGetAllCustomers() throws Exception {
		String uri = STARTING_URI + "/customer";
		List<Customer> allCustomers = Arrays.asList(
				new Customer(1, "compliance@OleMiss.edu", "password", "Hugh Freeze", Role.ROLE_CUSTOMER, true),
				new Customer(2, "test@test.com", "password", "Test Person", Role.ROLE_CUSTOMER, true));
		when(customerService.getAllCustomers()).thenReturn(allCustomers);
		mvc.perform(get(uri)).andDo(print()).andExpect(status().isOk())
			.andExpect(jsonPath("$.length()").value(allCustomers.size()))
			.andExpect(jsonPath("$[0].id").value(allCustomers.get(0).getId()))
			.andExpect(jsonPath("$[0].email").value(allCustomers.get(0).getEmail()))
			.andExpect(jsonPath("$[0].password").value(allCustomers.get(0).getPassword()))
			.andExpect(jsonPath("$[0].name").value(allCustomers.get(0).getName()))
			.andExpect(jsonPath("$[0].role").value(allCustomers.get(0).getRole()))
			.andExpect(jsonPath("$[0].enabled").value(allCustomers.get(0).isEnabled()))
			.andExpect(jsonPath("$[1].id").value(allCustomers.get(1).getId()))
			.andExpect(jsonPath("$[1].email").value(allCustomers.get(1).getEmail()))
			.andExpect(jsonPath("$[1].password").value(allCustomers.get(1).getPassword()))
			.andExpect(jsonPath("$[1].name").value(allCustomers.get(1).getName()))
			.andExpect(jsonPath("$[1].role").value(allCustomers.get(1).getRole()))
			.andExpect(jsonPath("$[1].enabled").value(allCustomers.get(1).isEnabled()));
		verify(customerService,times(1)).getAllCustomers();
		verifyNoInteractions(customerService);
	}

}
