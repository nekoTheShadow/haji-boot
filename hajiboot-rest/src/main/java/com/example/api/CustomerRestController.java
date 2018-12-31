package com.example.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.Customer;
import com.example.service.CustomerService;

@RestController
@RequestMapping("api/customers")
public class CustomerRestController {
	@Autowired
	CustomerService customerService;
	
	@GetMapping
	List<Customer> getCustomers() {
		return customerService.findAll();
	}
	
	@GetMapping(path = "{id}")
	Customer getCustomer(@PathVariable Integer id) {
		return customerService.findOne(id);
	}
}
