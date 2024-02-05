package com.demo.config;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import jakarta.persistence.EntityManagerFactory;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
		entityManagerFactoryRef = "bookEntityManagerFactory", 
		transactionManagerRef = "bookTransactionManager", 
		basePackages = {
				"com.demo.book.repository"
		}
		
)
public class BookDBConfig {

//	@Primary
//	@Bean(name = "bookDataSource")
//	@ConfigurationProperties(prefix = "spring.book.datasource")
//	public DataSource dataSource() {
//		return DataSourceBuilder.create().build();
//	}
	
	@Autowired
	private Environment env;
	
	@Primary
	@Bean(name = "bookDataSource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().url(env.getProperty("spring.book.datasource.url"))
				.driverClassName(env.getProperty("spring.book.datasource.driver-class-name"))
				.username(env.getProperty("spring.book.datasource.username"))
				.password(env.getProperty("spring.book.datasource.password")).build();
	}

	@Primary
	@Bean(name = "bookEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean bookEntityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("bookDataSource") DataSource dataSource) {
		
		HashMap<String, Object> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto", "update");
		
		return builder.dataSource(dataSource)
					  .properties(properties)
					  .packages("com.demo.book.model")
					  .persistenceUnit("book")  //set of entity classes which are mapped to database
					  .build();
	}

	@Primary
	@Bean(name = "bookTransactionManager")
	public PlatformTransactionManager bookTransactionManager(@Qualifier("bookEntityManagerFactory") EntityManagerFactory bookEntityManagerFactory) {
		return new JpaTransactionManager(bookEntityManagerFactory);  //Implmentation of PlatformTransactionManager,used to manage transactions when working with JPA-based data access in a Spring application.
	}
}
