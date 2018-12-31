package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@SpringBootApplication
public class HajibootJdbcApplication implements CommandLineRunner {
	@Autowired
	NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public void run(String... args) throws Exception {
		var sql = "SELECT 1";
		var param = new MapSqlParameterSource();
		var result = jdbcTemplate.queryForObject(sql, param, Integer.class);
		System.out.println("result = " + result);
	}
		
	public static void main(String[] args) {
		SpringApplication.run(HajibootJdbcApplication.class, args);
	}
}

