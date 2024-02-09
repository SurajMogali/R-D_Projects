package com.demo.spring.scheduler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

@Component
@EnableAsync
public class Scheduler {

	private Logger log = LoggerFactory.getLogger(Scheduler.class);

	// @Scheduled(fixedDelay = 3000)
	// @Scheduled(fixedRate = 3000,initialDelay=1000)
//	@Scheduled(fixedRateString = "PT02S",initialDelay=1000)
	@Async // For Parallel Execution
	@Scheduled(cron = "*/2 * * * * * ") // For Every 2 second it should execute
	// @Scheduled(cron= "0 */2 * * *  *") //For every 2 minutes it should execute
	// @Scheduled(cron= "0 10 20 * *  TUE") //On Tuesday, At 8:10 pm it should execute
	// @Scheduled(cron="${cron.expression.value}") //From App Properties

	public void scheduler() throws InterruptedException {
		LocalDateTime current = LocalDateTime.now();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		String formattedDateTime = current.format(format);
		log.info("Scheduler time " + formattedDateTime);
		Thread.sleep(1000);

	}

	

}
