package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.domain.Customer;
import com.example.repository.CustomerRepository;

@SpringBootApplication
public class HajibootJpaApplication implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(HajibootJpaApplication.class, args);
	}
	
	@Autowired
	CustomerRepository customerRepository;

	@Override
	public void run(String... args) throws Exception {
		var created = customerRepository.save(new Customer(null, "Hidetoshi", "Dekisugi"));
		System.out.println(created + " is created!");
		customerRepository.findAllOrderByName().forEach(System.out::println);
	}
}

