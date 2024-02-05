package com.demo.spring.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemController {

	private static final Logger logger = LoggerFactory.getLogger(ItemController.class);

	@GetMapping("/item")
	public String getItem() {
		logger.info("GetItem call returned");
		return "Item Selected successfully";
	}

}
