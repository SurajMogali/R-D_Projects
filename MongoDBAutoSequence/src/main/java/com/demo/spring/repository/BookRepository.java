package com.demo.spring.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.demo.spring.entity.Book;

public interface BookRepository extends MongoRepository<Book, Integer> {

}
