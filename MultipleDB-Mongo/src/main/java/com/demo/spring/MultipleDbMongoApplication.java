package com.demo.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

@SpringBootApplication
public class MultipleDbMongoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MultipleDbMongoApplication.class, args);
	}

}
