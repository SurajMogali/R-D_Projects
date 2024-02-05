package com.demo.spring.dao;

import java.util.Random;

import org.springframework.stereotype.Component;

import com.demo.spring.anno.MyTx;

@Component
public class EmployeeDao {

	public void saveEmployee() {
		System.out.println("From save Employee");
		if (new Random().nextInt(15) <= 10) {
			throw new RuntimeException("Business Class Exception");

		}

	}

	public String sayHello() {
		return "Hello";
	}

	@MyTx
	public void saveEmployee2() {
		System.out.println("From save Employee by annotation");

	}
}
