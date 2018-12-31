package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.PageRequest;

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
		
		var pageable = PageRequest.of(0, 3);
		var page = customerRepository.findAll(pageable);
		System.out.println("1ページのデータ数 = " + page.getSize());
		System.out.println("現在のページ = " + page.getNumber());
		System.out.println("全ページ数 = " + page.getTotalPages());
		System.out.println("全データ数 = " + page.getTotalElements());
		
		page.getContent().forEach(System.out::println);
	}
}

