package com.example.redispublisher.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.GenericToStringSerializer;

import io.lettuce.core.output.StreamingOutput.Subscriber;

@Configuration
public class RedisConfiguration {

    @Bean
   public  LettuceConnectionFactory lettuceConnectionFactory(){
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
        configuration.setHostName("localhost");
        configuration.setPort(6379);
        return new LettuceConnectionFactory(configuration);
    }
    
    @Bean
    @Primary
    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory redisConnectionFactory)
    {
        RedisTemplate<String,Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setValueSerializer(new GenericToStringSerializer<Object>(Object.class));
        return redisTemplate;
    }

    @Bean
    public ChannelTopic topic() {
        return new ChannelTopic("pubsub microservice");
    }

//    @Bean
//   public  MessageListenerAdapter messageListenerAdapter()
//    {
//
//        return new MessageListenerAdapter(new Subscriber);
//    }

   
    
//    @Bean
//    public MessagePublisher messagePublisher()
//    {
//        return new Publisher();
//    }
    
    

}
