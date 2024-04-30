package com.demo.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.spring.service.CustomerService;

@RestController
public class CustomerController {

	@Autowired
	private CustomerService cs;

	@PostMapping("/feedCustomerData")
	public void setDataInDB() {
		cs.saveCustomerData();
	}

}
