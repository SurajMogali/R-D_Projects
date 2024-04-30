package com.demo.spring.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.spring.entity.User;
import com.demo.spring.repository.UserRepository;

@Service
public class UserService {

	private UserRepository userRepository;

	private UserRepository userRepository2;

	@Transactional(transactionManager = "transactionManager")
	public User saveToBothDatabases(User user) {
		// Save to the write database
		User savedUser = userRepository.save(user);

		// Optionally, you can save to the read database if needed
		// YourEntity savedEntityRead = yourRepository.save(entity);

		return savedUser;
	}

//	@Transactional(transactionManager = "transactionManager2")
//	public User saveToBothDatabases2(User user) {
//		// Save to the write database
//		User savedUser2 = userRepository2.save(user);
//
//		// Optionally, you can save to the read database if needed
//		// YourEntity savedEntityRead = yourRepository.save(entity);
//
//		return savedUser2;
//	}
}
