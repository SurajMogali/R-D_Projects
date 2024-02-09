package com.demo.spring.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SampleService {
	
	
	public void doLongRunningTasks(String name) throws InterruptedException
	{
		System.out.println("Hello "+name+"from JobRunr");
		Thread.sleep(10000);
		
	}
	
//	private Logger log = LoggerFactory.getLogger(SampleService.class);
	
	
//	//@Scheduled(cron= "0 10 20 * *  TUE")	
//	@Scheduled(cron= "0 0 */3 * * *")
//	public void scheduler() throws InterruptedException {
//		LocalDateTime current = LocalDateTime.now();
//		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
//		String formattedDateTime = current.format(format);
//		log.info("Scheduler time " + formattedDateTime);
//		Thread.sleep(1000);

//	}
	
	public void myLongRunningMethod() throws InterruptedException
	{
		System.out.println("The job is started");
		Thread.sleep(5000);
		System.out.println("In the middle");
		Thread.sleep(5000);
		System.out.println("The job is done");
		
	}
	
	public void myLongRunningMethod2() throws InterruptedException
	{
		System.out.println("The job is started");
		Thread.sleep(5000);
		System.out.println("In the middle");
		Thread.sleep(5000);
		System.out.println("The job is done");
		
	}
	

}
