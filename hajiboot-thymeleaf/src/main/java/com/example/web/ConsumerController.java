package com.example.web;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Customer;
import com.example.service.CustomerService;

@Controller
@RequestMapping("customers")
public class ConsumerController {
	
	@Autowired
	CustomerService customerService;
	
	@ModelAttribute
	CustomerForm setUpForm() {
		return new CustomerForm();
	}
	
	@GetMapping
	String list(Model model) {
		var customers = customerService.findAll();
		model.addAttribute("customers", customers);
		return "customers/list";
	}
	
	@PostMapping(path="create")
	String create(@Validated CustomerForm form, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return list(model);
		}
		
		var customer = new Customer();
		BeanUtils.copyProperties(form, customer);
		customerService.create(customer);
		return "redirect:/customers";
	}
}
