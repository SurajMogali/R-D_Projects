package com.demo;

import com.demo.service.PaymentService;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

	public static void main(String[] args) throws BeansException {
		//System.out.println("Hello World");
		int amount=500;

		ApplicationContext context=new ClassPathXmlApplicationContext(  "config.xml");
		PaymentService paymentObject =context.getBean("payment",PaymentService.class);
        //auth,print:PaymentStarted
		paymentObject.makePayment(amount);
	}



}
