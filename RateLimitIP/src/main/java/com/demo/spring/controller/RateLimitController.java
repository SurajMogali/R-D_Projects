package com.demo.spring.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/rate-limit")
public class RateLimitController {

	@GetMapping
	public ResponseEntity<String> rateLimitTest1() {
		return ResponseEntity.ok("RUNNING SUCCESSFULLY");
	}

	@GetMapping("/two")
	public ResponseEntity<String> rateLimitTest2() {
		return ResponseEntity.ok("RUNNING SUCCESSFULLY");
	}

	@GetMapping("/disable")
	public ResponseEntity<String> rateLimitRemovedTest() {
		return ResponseEntity.ok("RUNNING SUCCESSFULLY");
	}

}
