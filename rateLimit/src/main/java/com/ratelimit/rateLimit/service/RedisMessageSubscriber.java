package com.ratelimit.rateLimit.service;


import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

@Service
public class RedisMessageSubscriber implements MessageListener {

    @Override
    public void onMessage(Message message, byte[] pattern) {
        byte[] body = message.getBody();
        String receivedMessage = new String(body);
        System.out.println("Received message: " + receivedMessage);
    }
}
