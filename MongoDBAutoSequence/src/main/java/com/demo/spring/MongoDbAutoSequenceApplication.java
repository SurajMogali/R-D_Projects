package com.demo.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication

public class MongoDbAutoSequenceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MongoDbAutoSequenceApplication.class, args);
	}

}
