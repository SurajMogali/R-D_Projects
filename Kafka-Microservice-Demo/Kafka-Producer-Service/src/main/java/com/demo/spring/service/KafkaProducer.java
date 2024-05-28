package com.demo.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {
	
	
	//Two parameters indicates the name of the topic and message

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	

	public void sendMessageToTopic(String message) {
		kafkaTemplate.send("neokred", message);
	}

}
