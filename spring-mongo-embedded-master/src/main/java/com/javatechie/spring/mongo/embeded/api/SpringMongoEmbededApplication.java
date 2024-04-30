package com.javatechie.spring.mongo.embeded.api;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@SpringBootApplication
public class SpringMongoEmbededApplication {


	public static void main(String[] args) {
		SpringApplication.run(SpringMongoEmbededApplication.class, args);
	}


	@Bean
	public OpenAPI openApiDoc() {
		return new OpenAPI().info(new Info().description("MongoDB Relation Project")
				.license(new License().name("Free for All")).title("Mock Project Document on MongoDB Relation Project").version("1.0.0-BETA"));
	}


}
