package com.demo.spring.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.demo.spring.entity.Author;

public interface AuthorRepository extends MongoRepository<Author, String> {
    // Add custom queries if needed
}
