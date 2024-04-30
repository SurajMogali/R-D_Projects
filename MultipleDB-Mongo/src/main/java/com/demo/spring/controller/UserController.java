package com.demo.spring.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.spring.entity.User;
import com.demo.spring.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	
	@Autowired
	private UserService userService;

	@GetMapping("/getAllUsers")
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}

	@GetMapping("/getUser/{id}")
	public Optional<User> getUserById(@PathVariable String id) {
		Optional<User> user = Optional.empty();
		return userService.getUserById(id);
	}

	@PostMapping("/save")
	public User createUser(@RequestBody User user) {
		return userService.createUser(user);
	}

	@PutMapping("/updateUser/{id}")
	public User updateUser(@PathVariable String id, @RequestBody User user) {
		User updatedUser = userService.updateUser(id, user);
		return userService.updateUser(id, updatedUser);
	}

	@DeleteMapping("/delete/{id}")
	public void deleteUser(@PathVariable String id) {
		userService.deleteUser(id);

	}
}
