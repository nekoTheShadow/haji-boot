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

@SpringBootApplication
public class HajibootJdbcApplication implements CommandLineRunner {
	@Autowired
	NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public void run(String... args) throws Exception {
		var sql = "SELECT id, first_name, last_name FROM customers WHERE id = :id";
		var param = new MapSqlParameterSource().addValue("id", 1);
		var result = jdbcTemplate.queryForObject(sql, param, new RowMapper<Customer>() {
			@Override
			public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
				return new Customer(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name"));
			}
		});
		System.out.println("result = " + result);	}
		
	public static void main(String[] args) {
		SpringApplication.run(HajibootJdbcApplication.class, args);
	}
}

