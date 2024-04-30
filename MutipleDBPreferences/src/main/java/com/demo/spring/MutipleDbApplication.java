package com.demo.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication(exclude = { HibernateJpaAutoConfiguration.class })
@Configuration
public class MutipleDbApplication {

	public static void main(String[] args) {
		SpringApplication.run(MutipleDbApplication.class, args);
	}

}
