package com.demo.spring;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.demo.spring.service.EmailSenderService;

@SpringBootApplication
@EnableScheduling
public class SpringEmailDemoApplication {

	@Autowired
	private EmailSenderService senderService;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringEmailDemoApplication.class, args);
	}
	
	@EventListener(ApplicationReadyEvent.class)
    @Scheduled(fixedRate = 60000)
	public void triggerMail() throws MessagingException {
		senderService.sendSimpleEmail("suraj@neokred.tech",
				"Testing Scheduler",
				"Scheduling in Springboot is done succesfully");

	}
}
