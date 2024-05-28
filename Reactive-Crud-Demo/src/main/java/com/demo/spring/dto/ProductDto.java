package com.demo.spring.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class ProductDto {
	
	
	@Id
	private String id;
	private String name;
	private int qty;
	private double price;
	

}
