package com.demo.spring.entity;

import java.time.LocalDate;

import lombok.Data;

@Data
public class Person {
	
	    String firstName;
	    String lastName;
	    LocalDate birthDate;
	    Address address;

}
