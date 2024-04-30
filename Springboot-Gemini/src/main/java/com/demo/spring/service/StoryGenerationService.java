package com.demo.spring.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class StoryGenerationService {

    public String generateStory(String promptText) {
        String apiKey = "AIzaSyAoWY0i8qWcOxatt3JarAWyRIcylDQpdFA";
        
        String apiUrl = "https://generativelanguage.googleapis.com/v1beta/models/gemini-pro:generateContent?key=" + apiKey;
        String requestBody = "{\"contents\":[{\"parts\":[{\"text\":\"" + promptText + "\"}]}]}";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.postForEntity(apiUrl, requestBody, String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        } else {
            return "Error: Failed to generate answer";
        }
    }
}
