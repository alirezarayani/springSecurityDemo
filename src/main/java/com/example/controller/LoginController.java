package com.example.controller;

import com.example.model.Customer;
import com.example.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Optional;

@RestController
public class LoginController {

	@Autowired
	private CustomerRepository customerRepository;
	
	@RequestMapping("/user")
	public Customer getUserDetailsAfterLogin(Principal user) {
		Optional<Customer> customer = customerRepository.findByEmail(user.getName());
		if (customer.isPresent()) {
			return customer.get();
		}else {
			return null;
		}
	}
}
