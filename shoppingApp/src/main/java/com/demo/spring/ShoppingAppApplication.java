package com.demo.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@SpringBootApplication

public class ShoppingAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoppingAppApplication.class, args);
	}

	@Bean
	public OpenAPI openApiDoc() {
		return new OpenAPI().info(new Info().description("Mock project on Shopping App")
				.license(new License().name("Free for All")).title("Mock Project Document").version("1.0.0-BETA"));
	}

	@Bean
	public Docket postsApi() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select().build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("simplifyingtech API").description("simplifyingtech API for developers")
				.termsOfServiceUrl("https://simplifyingtechcode.wordpress.com/").licenseUrl("simplifyingtech@gmail.com")
				.version("2.0").build();
	}

}
