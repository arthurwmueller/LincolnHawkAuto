package com.cognixia.jump.controller;

import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithMockUser;
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
	@MockBean
	AuthenticationManager authenticationManager;
	@MockBean
	UserDetailsService userDetailsService;
	@MockBean
	JwtUtil jwtUtil;

	@WithMockUser(value = "test@Test.com")
	@Test
	void testCreateCustomer() throws Exception {
		String uri = STARTING_URI + "/customer";
		Customer customer = new Customer(-1, "test@test.com", "password", "Test Person", Role.ROLE_CUSTOMER, true);
		when(customerService.createCustomer(Mockito.any(Customer.class))).thenReturn(customer);
		mvc.perform(post(uri).contentType(MediaType.APPLICATION_JSON_VALUE)).andDo(print())
				.andExpect(status().isCreated()).andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
		verify(customerService, times(1)).createCustomer(customer);
		verifyNoMoreInteractions(customerService);
	}

}
