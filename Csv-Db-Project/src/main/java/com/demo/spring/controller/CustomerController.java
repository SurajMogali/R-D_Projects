package com.demo.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.spring.service.CustomerService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class CustomerController {

	@Autowired
	private CustomerService cs;

	@PostMapping("/feedCustomerData")
	public void setDataInDB() {
		cs.saveCustomerData();
	}

	@GetMapping("/excel")
	public void generateExcelReport(HttpServletResponse response) throws Exception {

		response.setContentType("application/octet-stream");

		String headerKey = "Content-Disposition";
		String headerValue = "attachment;filename=customer_info.xls";

		response.setHeader(headerKey, headerValue);

		cs.generateExcel(response);

		response.flushBuffer();
	}

}
