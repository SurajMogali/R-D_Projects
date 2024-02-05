package com.demo.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

import java.util.stream.IntStream;

@SpringBootApplication
public class SpringResilience4jRatelimitApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringResilience4jRatelimitApplication.class, args);
		

	}

}
