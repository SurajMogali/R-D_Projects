package com.javatechie.spring.mongo.embeded.api.controller;

import java.util.List;

import com.javatechie.spring.mongo.embeded.api.model.Address;
import com.javatechie.spring.mongo.embeded.api.repository.AddressRepository;
import io.swagger.v3.oas.models.annotations.OpenAPI30;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.javatechie.spring.mongo.embeded.api.model.User;
import com.javatechie.spring.mongo.embeded.api.repository.FlipkartRepository;

@RestController
@RequestMapping("/order-service")
@OpenAPI30
public class FlipkartOMController {

	@Autowired
	private FlipkartRepository repository;

	@Autowired
	private AddressRepository addressRepository;

	@PostMapping("/placedOrderNow")
	public String placeOrder(@RequestBody User user) {
		Address address = user.getAddress();

		addressRepository.save(address);
		repository.save(user);
		return "Order placed successfully...";
	}

	@GetMapping("/getUserByName/{name}")
	public List<User> getUserbyName(@PathVariable String name) {
		return repository.findByName(name);
	}

	@GetMapping("/getUserByAddress/{city}")
	public List<User> getUserbyAddress(@PathVariable String city) {
		return repository.findByCity(city);
	}


	@GetMapping("/findAllAddress")
	public List<Address> findAllAddress()
	{
		return addressRepository.findAll();
	}




}
