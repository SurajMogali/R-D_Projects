package com.demo.spring.subscriber;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;



public class Receiver implements MessageListener {
	
	Logger logger =LoggerFactory.getLogger(Receiver.class);
	


	@Override
	public void onMessage(Message message, byte[] bytes) {

		logger.info("Consumed event {}",message);

		
	}

	
			
	
	

}
