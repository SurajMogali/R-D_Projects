package com.demo.spring.controller;

import java.time.Instant;

import org.jobrunr.scheduling.JobScheduler;
import org.jobrunr.scheduling.cron.Cron;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.spring.service.SampleService;

@RestController
public class SampleController {

	@Autowired
    private  JobScheduler jobScheduler;
	@Autowired
    private  SampleService sampleService;

   


    @GetMapping("/enqueue/one-job")
    public String enqueueOneJob(@RequestParam(name = "name", defaultValue = "world") String name) {
        jobScheduler.enqueue(() -> sampleService.doLongRunningTasks(name));
        
        
        return "One job Enqueued";
    }
    
//    @GetMapping("/displayTime")
//    public void enqueueTwoJob() {
//        jobScheduler.enqueue(() -> sampleService.scheduler());
//        System.out.println("Display method has been scheduled");
//        
//    }
    @GetMapping("/schedule/one-job")
    public String ScheduleOneJob() {
    	jobScheduler.schedule(Instant.now().plusSeconds(60),()->sampleService.myLongRunningMethod());
    	//jobScheduler.schedule(Instant.now().plusMillis(5000),()->sampleService.myLongRunningMethod());
    	//jobScheduler.schedule(Instant.now().plusNanos(3000000000),()->sampleService.myLongRunningMethod());
        return "One job Scheduled";
        
    }
    
    @GetMapping("/recurring/one-job")
    public String ScheduleRecurringJob() {
    	jobScheduler.scheduleRecurrently(Cron.daily(),()->sampleService.myLongRunningMethod2());
    	//jobScheduler.scheduleRecurrently(Cron.everyHalfHour(),()->sampleService.myLongRunningMethod2());
        return "One job is Scheduled Recurrently ";
    }
    
    
    
    
    
    
}
