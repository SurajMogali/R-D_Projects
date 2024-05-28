package com.demo.spring.dao;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Component;

import com.demo.spring.dto.Customer;

import reactor.core.publisher.Flux;

@Component
public class CustomerDao {

	private static void sleepExecution(int i) {
		try {
			Thread.sleep(1000);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public List<Customer> getCustomers() {
		
		//generate the integers from 1 to 10
		return IntStream.rangeClosed(1, 10).peek(CustomerDao::sleepExecution)
				.peek(i -> System.out.println("processing count : " + i)).mapToObj(i -> new Customer(i, "customer" + i))
				.collect(Collectors.toList());
	}
	
	
	//This method returns a Flux.range(1,10) to create a range of integers from 1 to 10
	public Flux<Customer> getCustomersStream() {
		return Flux.range(1, 10)
				.delayElements(Duration.ofSeconds(1))
				.doOnNext(i -> System.out.println("processing count : " + i))
				.map(i -> new Customer(i, "customer" + i));
	}
	
	
	// Similar to getCustomersStream but creates a larger range (Flux.range(1, 50)).
	 public Flux<Customer> getCustomerList()  {
	        return Flux.range(1,50)
	                .doOnNext(i -> System.out.println("processing count in stream flow : " + i))
	                .map(i -> new Customer(i, "customer" + i));
	    }
	 
	 
	 

}
