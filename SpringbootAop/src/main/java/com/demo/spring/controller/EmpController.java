package com.demo.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.spring.entity.Employee;
import com.demo.spring.service.EmpService;

@RestController
@RequestMapping(path = "/employees")

public class EmpController {

	@Autowired
	private EmpService empService;

	

	@GetMapping("/getAll")
	public List<Employee> getAllEmployees() {
		return empService.getAllEmployees();
	}

	

}
