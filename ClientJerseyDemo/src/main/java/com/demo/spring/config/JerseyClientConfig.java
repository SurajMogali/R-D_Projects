package com.demo.spring.config;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.ws.rs.client.Client;

@Configuration
public class JerseyClientConfig {

    
    public Client jerseyClient() {
        ClientConfig clientConfig = new ClientConfig();
        clientConfig.property(ClientProperties.CONNECT_TIMEOUT, 5000); // Set your connect timeout
        clientConfig.property(ClientProperties.READ_TIMEOUT, 15000); // Set your read timeout
        return JerseyClientBuilder.newClient(clientConfig);
    }
}
