package com.example;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;

import org.hamcrest.MatcherAssert;
import org.hamcrest.beans.SamePropertyValuesAs;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.hamcrest.HamcrestArgumentMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.domain.Customer;
import com.example.repository.CustomerRepository;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ibm.db2.jcc.am.cu;

import lombok.Data;

@RunWith(SpringRunner.class)
@SpringBootTest(
	webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
	properties = {"jdbc:log4jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE"}
)
public class HajibootRestApplicationTests {
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	TestRestTemplate restTemplate;
	
	Customer customer1;
	Customer customer2;
	
	@Data
	@JsonIgnoreProperties(ignoreUnknown=true)
	static class Page<T> {
		private List<T> content;
		private int numberOfElements;
	}
	
	@Before
	public void setUp() {
		customerRepository.deleteAll();
		
		customer1 = new Customer();
		customer1.setFirstName("Taro");
		customer1.setLastName("Yamada");
		
		customer2 = new Customer();
		customer2.setFirstName("Ichiro");
		customer2.setLastName("Suzuki");
		
		customerRepository.saveAll(List.of(customer1, customer2));
	}
	
	@Test
	public void testGetCustomers() {
		var response = restTemplate.exchange(
			"/api/customers", 
			HttpMethod.GET, 
			null, 
			new ParameterizedTypeReference<Page<Customer>>() {}
		);
		
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody().getNumberOfElements()).isEqualTo(2);
		
		var c1 = response.getBody().getContent().get(0);
		var c2 = response.getBody().getContent().get(1);
		assertThat(c1).isEqualToComparingFieldByField(customer2);
		assertThat(c2).isEqualToComparingFieldByField(customer1);
	}
	
	@Test
	public void testPostCustomers() {
		var customer3 = new Customer();
		customer3.setFirstName("Nobita");
		customer3.setLastName("Nobi");
		
		var postResponse = restTemplate.exchange(
				"/api/customers",
				HttpMethod.POST,
				new HttpEntity<Object>(customer3),
				Customer.class);
		assertThat(postResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		
		var customer = postResponse.getBody();
		assertThat(customer.getId()).isNotNull();
		assertThat(customer.getFirstName()).isEqualTo(customer3.getFirstName());
		assertThat(customer.getLastName()).isEqualTo(customer3.getLastName());
		
		var getResponse = restTemplate.exchange(
				"/api/customers", 
				HttpMethod.GET, 
				null, 
				new ParameterizedTypeReference<Page<Customer>>() {}
		);
		assertThat(getResponse.getBody().getNumberOfElements()).isEqualTo(3);
	}
	
	@Test
	public void testDeleteCustomers() {
		var deleteResponse = restTemplate.exchange(
			"/api/customers/{id}", 
			HttpMethod.DELETE, 
			null,
			Void.class,
			Map.of("id", customer1.getId())
		);
		assertThat(deleteResponse.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
		
		var getResponse = restTemplate.exchange(
				"/api/customers", 
				HttpMethod.GET, 
				null, 
				new ParameterizedTypeReference<Page<Customer>>() {}
		);
		assertThat(getResponse.getBody().getNumberOfElements()).isEqualTo(1);
	}
}
