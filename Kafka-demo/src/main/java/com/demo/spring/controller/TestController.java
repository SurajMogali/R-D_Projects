package com.demo.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.spring.producer.Producer;

@RestController
public class TestController {

	private final Producer producer;

	@Autowired
	public TestController(Producer producer) {
		this.producer = producer;
	}

	@PostMapping("/publish")
	public void messageToTopic(@RequestParam("message") String message) {

		this.producer.sendMessage(message);

	}
}
