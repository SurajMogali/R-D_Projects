package com.demo.spring.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.spring.service.VertexAiService;

@RestController
public class VertexAiController {


	@Autowired 
	VertexAiService vertexAiService;

	@GetMapping("/answer")
	public String fetchAnswer() throws IOException {
		return vertexAiService.showOutput();

	}

}
