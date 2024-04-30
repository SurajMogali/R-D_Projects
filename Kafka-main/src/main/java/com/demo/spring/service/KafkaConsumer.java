package com.demo.spring.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

	@KafkaListener(topics = "neokred", groupId = "neokred-group")
	public void listenToNeokredTopic(String messageReceived) {
		System.out.println("Message received is " + messageReceived);
	}
}
