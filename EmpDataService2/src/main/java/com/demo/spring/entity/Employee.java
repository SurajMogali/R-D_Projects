package com.demo.spring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "employee")

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
	@Id
	private Integer empId;
	private String empFirstName;
	private String empLastName;
	private String email;

	private Integer age;



}
