package com.demo.spring.controller;

import java.time.Duration;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;

@RestController
@RequestMapping("/ratelimit")
public class RateLimitController {

	private Bucket bucket;

	@GetMapping("/tokengenerate")
	public ResponseEntity<String> generateToken() {
		// refill
		Refill refill = Refill.of(5, Duration.ofMinutes(1));  //create 5 tokens per minute.For each api hit,one token will be consumed.

		// bucket
		bucket = Bucket4j.builder().addLimit(Bandwidth.classic(5, refill)) // Limited to 5
				.build();
		
		System.out.println(bucket);

		return new ResponseEntity<String>("Generated Successfully!!", HttpStatus.OK);

	}

	@GetMapping("/demo")
	public ResponseEntity<String> demo() {
		if (bucket.tryConsume(1))// If it consumes,then there is atleast one token available.
		{

			System.out.println("==============API working successfully=========");
			return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		}
		System.out.println("==============Number of Hits Exceeded=========");
		return new ResponseEntity<String>("TOO MANY HITS!!!!", HttpStatus.TOO_MANY_REQUESTS);

	}

}
