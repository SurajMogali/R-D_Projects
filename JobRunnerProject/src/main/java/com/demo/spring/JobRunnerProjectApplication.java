package com.demo.spring;

import java.time.Instant;

import org.jobrunr.configuration.JobRunr;
import org.jobrunr.scheduling.JobScheduler;
import org.jobrunr.storage.InMemoryStorageProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.demo.spring.service.SampleService;

@EnableScheduling
@SpringBootApplication
public class JobRunnerProjectApplication {
	
	
			
	

	public static void main(String[] args) {
		SpringApplication.run(JobRunnerProjectApplication.class, args);
		
//		JobScheduler jobScheduler =JobRunr
//				.configure()
//				.useStorageProvider(new InMemoryStorageProvider())
//				.useBackgroundJobServer()
//				.useDashboard()
//				.initialize()
//				.getJobScheduler();
//		
//		SampleService sampleService=new SampleService();
//		jobScheduler.enqueue(()->sampleService.myLongRunningMethod());
		
		
		
		
		
		
		
		
		
		
	}

}
