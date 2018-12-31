package com.example.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.service.CustomerService;

@Controller
@RequestMapping("customers")
public class ConsumerController {
	
	@Autowired
	CustomerService customerService;
	
	@GetMapping
	String list(Model model) {
		var customers = customerService.findAll();
		model.addAttribute("customers", customers);
		return "customers/list";
	}
}
