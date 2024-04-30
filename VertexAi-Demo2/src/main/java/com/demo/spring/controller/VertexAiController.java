package com.demo.spring.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.spring.service.VertexAiService;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.vertexai.VertexAiGeminiChatModel;

@RestController
public class VertexAiController {


	@Autowired 
	VertexAiService vertexAiService;

	@GetMapping("/answer")
	public String fetchAnswer() throws IOException {
		return vertexAiService.showOutput();

	}
	
	@GetMapping("/answer2")
	public String fetchAnswer2() throws IOException {
		 ChatLanguageModel model = VertexAiGeminiChatModel.builder()
                 .project("abstract-bongo-418310")
                 .location("us-central1")
                 .modelName("gemini-pro-vision")
                 .build();

         String response = model.generate("Tell me a joke");

        return response;

	}
	
	

}
