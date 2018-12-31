package com.example;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.example.domain.Customer;
import com.example.repository.CustomerRepository;

@SpringBootApplication
public class HajibootJdbcApplication implements CommandLineRunner {
	@Autowired
	CustomerRepository customerRepository;

	@Override
	public void run(String... args) throws Exception {
		var created = customerRepository.save(new Customer(null, "Hidetoshi", "Dekisugi"));
		System.out.println(created + " is created!");
		customerRepository.findAll().forEach(System.out::println);
	}
		
	public static void main(String[] args) {
		SpringApplication.run(HajibootJdbcApplication.class, args);
	}
}

