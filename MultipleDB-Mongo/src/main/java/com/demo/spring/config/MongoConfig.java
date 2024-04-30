package com.demo.spring.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ReadPreference;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Configuration
public class MongoConfig {

	@Value("${spring.data.mongodb.uri}")
	private String readUri;

//	@Bean
//	public MongoClient readMongoClient() {
//		ConnectionString connectionString = new ConnectionString(readUri);
//		MongoClientSettings settings = MongoClientSettings.builder().applyConnectionString(connectionString)
//				// Set read preference or any additional configurations for read operations
//				// Reads data from secondary replica set (if available) otherwise fall back to
//				// primary replica set to read data
//				.readPreference(ReadPreference.secondary()).retryReads(true).build();
//
//		return MongoClients.create(settings);
//	}
}
