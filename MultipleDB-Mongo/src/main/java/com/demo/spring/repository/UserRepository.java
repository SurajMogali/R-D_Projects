package com.demo.spring.repository;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.demo.spring.entity.User;

public interface UserRepository extends MongoRepository<User, String> {
}
