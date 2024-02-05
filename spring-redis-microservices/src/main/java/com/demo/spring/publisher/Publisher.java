package com.demo.spring.publisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.spring.dto.Product;

@RestController
public class Publisher implements MessagePublisher {

	Logger logger = LoggerFactory.getLogger(Publisher.class);
	@Autowired
	private RedisTemplate template;
	
	
	@Autowired
	private ChannelTopic topic;




	@PostMapping("/publish")
	public String publish(@RequestBody Product product)
	{

        logger.info("Message is published by the Publisher");
		template.convertAndSend(topic.getTopic(), product.toString());
		return "Event published !!";
	}




	@Override
	public void publisher(String message) {
		template.convertAndSend(topic.getTopic(),message);
		
		
	}

	
	
	
	
	
	
	
	
	

}
