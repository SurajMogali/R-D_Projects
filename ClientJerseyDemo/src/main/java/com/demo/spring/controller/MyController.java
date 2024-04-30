package com.demo.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.demo.spring.service.MyService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestController
public class MyController {
	
	@Autowired
	MyService  myService;
	
	@GetMapping(path="/getPayload")
	public String sendMessage()
	{
		 return myService.sendMessage();
		
		
	}
	
	@GetMapping(path="/objectFetch")
	public void objectFetcher() throws JsonMappingException, JsonProcessingException
	{
		  myService.objectFetch();
		
		
	}
	
	
	
	
	
	
	

}
