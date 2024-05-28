package com.demo.spring.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.demo.spring.dao.CustomerDao;
import com.demo.spring.dto.Customer;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerHandler {

	@Autowired
	private CustomerDao dao;
	
	//Since Flux represents a stream of multiple customer objects,
	//this method will asynchronously send each customer as it becomes available.

	public Mono<ServerResponse> loadCustomers(ServerRequest request) {
		Flux<Customer> customerList = dao.getCustomerList();
		return ServerResponse.ok().body(customerList, Customer.class);
	}

	public Mono<ServerResponse> findCustomer(ServerRequest request) {
		int customerId = Integer.valueOf(request.pathVariable("input"));
		// dao.getCustomerList().filter(c->c.getId()==customerId)CustomerHandler.take(1).single();
		
		//next() will convert the Flux to Mono,representing the first matching customer
		
		//Mono represents zero or one customer object, making it suitable for finding a specific customer.	
		Mono<Customer> customerMono = dao.getCustomerList().filter(c -> c.getId() == customerId).next();
		return ServerResponse.ok().body(customerMono, Customer.class);

	}

	public Mono<ServerResponse> saveCustomer(ServerRequest request) {
		Mono<Customer> customerMono = request.bodyToMono(Customer.class);
		Mono<String> saveResponse = customerMono.map(dto -> dto.getId() + ":" + dto.getName());
		return ServerResponse.ok().body(customerMono, Customer.class);
	}

}
