package com.demo.spring.consumer;

import org.springframework.kafka.annotation.KafkaListener;

public class Consumer {
	
	@KafkaListener(topics="test_topic",groupId= "group_id")
	public void consumeMessage(String message)
	{
		System.out.println(message);
	}

}
