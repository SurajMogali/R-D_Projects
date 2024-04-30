package com.javatechie.spring.mongo.embeded.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Address")
public class Address {

	@Id
	private Integer id;
	private String city;
	private String state;
	private String pincode;
}
