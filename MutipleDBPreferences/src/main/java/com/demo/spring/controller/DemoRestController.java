package com.demo.spring.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.spring.entity.User;
import com.demo.spring.repository.UserRepository;

@RestController
public class DemoRestController {

	@Autowired
	private UserRepository userRepository;

//	@GetMapping("/addData")
//	public String addData2DB() {
//		userRepository.saveAll(Stream.of(new User(744, "Thejus"), new User(455, "Suraj")).collect(Collectors.toList()));
//		// bookRepository.saveAll(Stream.of(new Book(111, "Core Java"), new Book(222,
//		// "Spring Boot")).collect(Collectors.toList()));
//		return "Data Added Successfully";
//	}

	@PostMapping("/save")
	public User saveUser(@RequestBody User user) {
		return userRepository.save(user);

	}

	@GetMapping("/getUser/{userId}")
	public Optional<User> getUser(@PathVariable Integer id) {
		return userRepository.findById(id);

	}

	public String updateUser(@RequestBody User user) {
		if (userRepository.existsById(user.getId())) {
			userRepository.save(user);
			return "User details updated";
		} else
			return "User does not Exist";

	}

	@DeleteMapping("/deleteUser")
	public void deleteUser(@PathVariable Integer id) {
		userRepository.deleteById(id);

	}

	@GetMapping("/getUsers")
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

}
