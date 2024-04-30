package com.demo.spring.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Document(collection="Book")
public class Book {
	
	@Id
	@Field(name="Id")
	private int id;
	
	@Field(name="Author_firstName")
	private String authorFirstName;
	
	
	@Field(name="Author_lastName")
	private String authorLastName;
	
	@Field(name="Book_Name")
	private String bookName;
	
	
	
	

}
